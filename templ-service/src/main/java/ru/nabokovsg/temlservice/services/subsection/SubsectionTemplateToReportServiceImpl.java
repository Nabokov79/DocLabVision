package ru.nabokovsg.temlservice.services.subsection;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.client.TemplateClient;
import ru.nabokovsg.temlservice.dto.client.ObjectsTypeDto;
import ru.nabokovsg.temlservice.dto.division.Division;
import ru.nabokovsg.temlservice.dto.subsection.NewSectionSubsectionTemplateDto;
import ru.nabokovsg.temlservice.dto.subsection.NewSubsectionTemplateToReportDto;
import ru.nabokovsg.temlservice.dto.report.ReportTemplateDto;
import ru.nabokovsg.temlservice.exceptions.BadRequestException;
import ru.nabokovsg.temlservice.exceptions.NotFoundException;
import ru.nabokovsg.temlservice.mappers.ReportTemplateMapper;
import ru.nabokovsg.temlservice.mappers.SubsectionTemplateMapper;
import ru.nabokovsg.temlservice.models.ReportTemplate;
import ru.nabokovsg.temlservice.models.SectionTemplate;
import ru.nabokovsg.temlservice.models.SubsectionDataTemplate;
import ru.nabokovsg.temlservice.models.SubsectionTemplate;
import ru.nabokovsg.temlservice.repository.SubsectionTemplateRepository;
import ru.nabokovsg.temlservice.services.SectionTemplateService;
import ru.nabokovsg.temlservice.services.converters.ConvertDivisionDataService;
import ru.nabokovsg.temlservice.services.converters.ConvertDocumentationDataService;
import ru.nabokovsg.temlservice.services.converters.ConvertToEnumService;
import ru.nabokovsg.temlservice.services.report.ReportTemplateService;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubsectionTemplateToReportServiceImpl implements SubsectionTemplateToReportService {

    private final SubsectionTemplateRepository repository;
    private final SubsectionTemplateMapper mapper;
    private final ReportTemplateService reportService;
    private final SectionTemplateService sectionService;
    private final ReportTemplateMapper reportMapper;
    private final ConvertDocumentationDataService documentationConvert;
    private final ConvertDivisionDataService divisionConvert;
    private final TemplateClient client;
    private final ConvertToEnumService enumService;

    @Override
    public ReportTemplateDto save(NewSubsectionTemplateToReportDto templateDto) {
        ReportTemplate report = reportService.getById(templateDto.getObjectsTypeId()
                                                    , templateDto.getReportingDocumentId());
        if (report != null) {
            SectionTemplate section = report.getSectionTemplates()
                                            .stream()
                                            .collect(Collectors.toMap(SectionTemplate::getId, s -> s))
                                            .get(templateDto.getSectionId());
            List<NewSectionSubsectionTemplateDto> subsectionsDto = filter(templateDto.getSubsectionTemplates()
                                                                        , section.getSubsectionsTemplates());
            if (subsectionsDto.isEmpty()) {
                return reportMapper.mapToReportTemplateDto(report);
            }
            List<SubsectionTemplate> subsections = subsectionsDto.stream()
                    .map(s -> {
                        SubsectionTemplate subsection = mapper.mapToNewSubsectionTemplate(s);
                        return setSubsectionTemplateValue(subsection
                                , templateDto.getObjectsTypeId()
                                , new Division(s.getDivisionId()
                                              , enumService.convertToDivisionType(s.getDivisionType())
                                              , s.getDivisionName())
                                            );
                    })
                    .toList();
            subsections = repository.saveAll(subsections);
            if (section.getSubsectionsTemplates() == null) {
                section.setSubsectionsTemplates(subsections);
            } else {
                section.getSubsectionsTemplates().addAll(subsections);
            }
            return reportMapper.mapToReportTemplateDto(setSectionTemplate(report, section));
        }
        throw new NotFoundException(String.format("Report template by objectsTypeId=%s, reportingDocumentId=%s"
                                                                               , templateDto.getObjectsTypeId()
                                                                               , templateDto.getReportingDocumentId()));
    }

    private ReportTemplate setSectionTemplate(ReportTemplate report, SectionTemplate section) {
        Map<Long, SectionTemplate> sections = report.getSectionTemplates().stream()
                                                             .collect(Collectors.toMap(SectionTemplate::getId, s -> s));
        sections.put(section.getId(), sectionService.updateSectionTemplate(section));
        report.setSectionTemplates(sections.values().stream().toList());
        return report;
    }

    private SubsectionTemplate setSubsectionTemplateValue(SubsectionTemplate subsection, Long objectsTypeId, Division division) {
        ObjectsTypeDto objectsType = client.getObjectsType(objectsTypeId);
        List<SubsectionDataTemplate> data = subsection.getSubsectionData();
        if (subsection.getSubsectionDataType() == null) {
            return subsection;
        }
        switch (subsection.getSubsectionDataType()) {
            case ALL_DOCUMENT, REGULATORY_DOCUMENT, METHODOLOGICAL_DOCUMENT -> data.addAll(documentationConvert.convert(subsection.getSubsectionDataType(), objectsType));
            case LABORATORY_DATA_EMPLOYEE_CERTIFICATION -> data.add(divisionConvert.convert(division));
            default -> throw new BadRequestException(String.format("SubsectionDataType=%s is not supported", subsection.getSubsectionDataType()));
        }
        if(subsection.getSubsectionData() == null) {
            subsection.setSubsectionData(data);
        } else {
            subsection.getSubsectionData().addAll(data);
        }
        return subsection;
    }

    private List<NewSectionSubsectionTemplateDto> filter(List<NewSectionSubsectionTemplateDto> subsections, List<SubsectionTemplate> subsectionsDb) {
        if (subsectionsDb.isEmpty()) {
            return subsections;
        }
        List<String> subsectionNames = subsectionsDb.stream()
                                                    .map(SubsectionTemplate::getSubsectionName)
                                                    .toList();
        subsections = subsections.stream()
                                 .filter(s -> !subsectionNames.contains(s.getSubsectionName()))
                                 .toList();
        return subsections;
    }

}