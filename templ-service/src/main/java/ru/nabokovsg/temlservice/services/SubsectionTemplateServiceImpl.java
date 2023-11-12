package ru.nabokovsg.temlservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.client.TemplateClient;
import ru.nabokovsg.temlservice.dto.client.ObjectsTypeDto;
import ru.nabokovsg.temlservice.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.temlservice.dto.report.ReportTemplateDto;
import ru.nabokovsg.temlservice.dto.subsection.NewProtocolSubsectionTemplate;
import ru.nabokovsg.temlservice.dto.subsection.NewSectionSubsectionTemplate;
import ru.nabokovsg.temlservice.dto.subsection.NewSubsectionTemplateDto;
import ru.nabokovsg.temlservice.mappers.ProtocolTemplateMapper;
import ru.nabokovsg.temlservice.mappers.ReportTemplateMapper;
import ru.nabokovsg.temlservice.mappers.SubsectionTemplateMapper;
import ru.nabokovsg.temlservice.models.*;
import ru.nabokovsg.temlservice.repository.SubsectionTemplateRepository;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubsectionTemplateServiceImpl implements SubsectionTemplateService {

    private final SubsectionTemplateRepository repository;
    private final SubsectionTemplateMapper mapper;
    private final ReportTemplateService reportService;
    private final ReportTemplateMapper reportMapper;
    private final ProtocolTemplateService protocolService;
    private final ProtocolTemplateMapper protocolMapper;
    private final SectionTemplateService sectionService;
    private final TemplateClient client;
    private final SubsectionDataTemplateService subsectionDataService;

    @Override
    public ReportTemplateDto addToSectionTemplate(NewSectionSubsectionTemplate sectionsDto) {
        ReportTemplate report = reportService.getById(sectionsDto.getObjectsTypeId()
                , sectionsDto.getReportingDocumentId());
        if (report != null) {
            SectionTemplate sectionDb = report.getSections()
                    .stream()
                    .collect(Collectors.toMap(SectionTemplate::getId, s -> s))
                    .get(sectionsDto.getSectionId());
            if (sectionDb != null) {
                List<NewSubsectionTemplateDto> subsections = filter(sectionsDto.getSubsections()
                        , sectionDb.getSubsections());
                if (subsections.isEmpty()) {
                    sectionDb.setSubsections(saveAll(sectionsDto.getSubsections(), sectionsDto.getObjectsTypeId()));
                } else {
                    sectionDb.getSubsections().addAll(saveAll(subsections, sectionsDto.getObjectsTypeId()));
                }
            }
            SectionTemplate section = sectionService.saveSection(sectionDb);
            report.setSections(report.getSections().stream()
                    .map(s -> {
                        if (s.getId() == section.getId()) {
                            s = section;
                        }
                        return s;
                    })
                    .toList());
        }
        return reportMapper.mapToReportTemplateDto(report);
    }

    @Override
    public ProtocolTemplateDto addToProtocolTemplate(NewProtocolSubsectionTemplate sectionsDto) {
        ProtocolTemplate protocol = protocolService.getById(sectionsDto.getObjectsTypeId()
                , sectionsDto.getReportingDocumentId());
        if (protocol != null) {
            List<NewSubsectionTemplateDto> subsections = filter(sectionsDto.getSubsections()
                    , protocol.getSubsections());
            if (subsections.isEmpty()) {
                protocol.setSubsections(saveAll(subsections, sectionsDto.getObjectsTypeId()));
            } else {
                protocol.getSubsections().addAll(saveAll(subsections, sectionsDto.getObjectsTypeId()));
            }
        }
        return protocolMapper.mapToProtocolTemplateDto(protocol);
    }

    @Override
    public SubsectionTemplate save(SubsectionTemplate subsections) {
        return repository.save(subsections);
    }

    private List<SubsectionTemplate> saveAll(List<NewSubsectionTemplateDto> subsections, Long objectsTypeId) {
        ObjectsTypeDto objectType = client.getObjectsType(objectsTypeId);
        return repository.saveAll(subsections.stream()
                .map(s -> {
                    SubsectionTemplate subsection = mapper.mapToNewSubsectionTemplate(s);
                    List<SubsectionDataTemplate> data = subsectionDataService.save(objectType
                                                                            , mapper.mapToNewSubsectionDataTemplate(s));
                    if (!data.isEmpty()) {
                        subsection.setSubsectionData(data);
                    }
                    return subsection;
                })
                .toList());
    }

    private List<NewSubsectionTemplateDto> filter(List<NewSubsectionTemplateDto> subsections
            , List<SubsectionTemplate> subsectionsDb) {
        Set<String> subsectionNames = subsectionsDb.stream()
                .map(SubsectionTemplate::getSubsectionName)
                .collect(Collectors.toSet());
        return subsections.stream()
                .filter(s -> !subsectionNames.contains(s.getSubsectionName()))
                .toList();
    }
}