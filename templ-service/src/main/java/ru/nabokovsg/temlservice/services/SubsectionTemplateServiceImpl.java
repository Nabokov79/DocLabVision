package ru.nabokovsg.temlservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.dto.subsection.NewSectionSubsectionTemplateDto;
import ru.nabokovsg.temlservice.dto.subsection.SubsectionTemplateDto;
import ru.nabokovsg.temlservice.mappers.SubsectionTemplateMapper;
import ru.nabokovsg.temlservice.models.ProtocolTemplate;
import ru.nabokovsg.temlservice.models.ReportTemplate;
import ru.nabokovsg.temlservice.models.SectionTemplate;
import ru.nabokovsg.temlservice.models.SubsectionTemplate;
import ru.nabokovsg.temlservice.repository.SubsectionTemplateRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubsectionTemplateServiceImpl implements SubsectionTemplateService {

    private final SubsectionTemplateRepository repository;
    private final SubsectionTemplateMapper mapper;
    private final ConvertStringToEnumService convert;
    private final ReportTemplateService reportTemplateService;
    private final ProtocolTemplateService protocolTemplateService;
    private final SectionTemplateService sectionTemplateService;

    @Override
    public List<SubsectionTemplateDto> save(NewSectionSubsectionTemplateDto templateDto) {
        if (templateDto.isProtocolTemplate()) {
            return addToProtocolTemplate(templateDto);
        } else {
            return addToReportTemplate(templateDto);
        }
    }

    private List<SubsectionTemplateDto> addToReportTemplate(NewSectionSubsectionTemplateDto templateDto) {
        ReportTemplate reportTemplate = reportTemplateService.getById(templateDto.getObjectsTypeId()
                , templateDto.getReportingDocumentId());
        if (reportTemplate != null) {
            List<SubsectionTemplate> subsectionTemplates = repository.saveAll(
                    templateDto.getSubsectionTemplates()
                            .stream()
                            .map(s -> {
                                SubsectionTemplate t = mapper.mapToNewSubsectionTemplate(s);
                                t.setSubsectionDataType(convert.convertSubsectionDataType(s.getSubsectionDataType()));
                                return t;
                            })
                            .toList());
            SectionTemplate sectionTemplate = sectionTemplateService.getById(templateDto.getSectionId());
            sectionTemplate.getSubsectionsTemplates().addAll(subsectionTemplates);
            sectionTemplateService.saveTemplate(sectionTemplate);
            return subsectionTemplates.stream()
                    .map(mapper::mapToSubsectionTemplateDto)
                    .toList();
        }
        return new ArrayList<>();
    }

    private List<SubsectionTemplateDto> addToProtocolTemplate(NewSectionSubsectionTemplateDto templateDto) {
        Set<ProtocolTemplate> protocolTemplates = protocolTemplateService.getAllByIds(templateDto.getObjectsTypeId()
                , templateDto.getReportingDocumentId());
        if (!protocolTemplates.isEmpty()) {
            List<SubsectionTemplate> subsectionTemplates = repository.saveAll(
                    templateDto.getSubsectionTemplates()
                            .stream()
                            .map(s -> {
                                SubsectionTemplate t = mapper.mapToNewSubsectionTemplate(s);
                                t.setSubsectionDataType(convert.convertSubsectionDataType(s.getSubsectionDataType()));
                                return t;
                            })
                            .toList());
            protocolTemplateService.saveAll(protocolTemplates
                    .stream()
                    .peek(p -> p.getSubsectionsTemplate().addAll(subsectionTemplates))
                    .collect(Collectors.toSet()));
            return subsectionTemplates.stream()
                    .map(mapper::mapToSubsectionTemplateDto)
                    .toList();
        }
        return new ArrayList<>();
    }
}