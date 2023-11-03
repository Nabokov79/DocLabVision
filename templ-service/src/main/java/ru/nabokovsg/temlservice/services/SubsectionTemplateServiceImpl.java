package ru.nabokovsg.temlservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.client.TemplateClient;
import ru.nabokovsg.temlservice.dto.client.*;
import ru.nabokovsg.temlservice.dto.section.SectionTemplateDto;
import ru.nabokovsg.temlservice.dto.subsection.NewSectionSubsectionTemplateDto;
import ru.nabokovsg.temlservice.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.temlservice.dto.subsection.NewSubsectionTemplateDto;
import ru.nabokovsg.temlservice.enums.DivisionType;
import ru.nabokovsg.temlservice.mappers.SubsectionTemplateMapper;
import ru.nabokovsg.temlservice.models.SubsectionDataTemplate;
import ru.nabokovsg.temlservice.models.SubsectionTemplate;
import ru.nabokovsg.temlservice.repository.SubsectionTemplateRepository;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class SubsectionTemplateServiceImpl implements SubsectionTemplateService {

    private final SubsectionTemplateRepository repository;
    private final SubsectionTemplateMapper mapper;

    private final ProtocolTemplateService protocolTemplateService;
    private final SectionTemplateService sectionTemplateService;
    private final TemplateClient client;
    private final SubsectionDataService subsectionDataService;
    private final ConvertStringToEnumService convert;

    @Override
    public SectionTemplateDto saveFromReportTemplate(NewSectionSubsectionTemplateDto templateDto) {
        return sectionTemplateService.addSubsectionTemplate(save(templateDto.getObjectsTypeId(), templateDto.getReportingDocumentId(), templateDto.getSubsectionTemplates()));
    }

    @Override
    public ProtocolTemplateDto saveFromProtocolTemplate(NewSectionSubsectionTemplateDto templateDto) {
        return protocolTemplateService.addSubsectionTemplate(save(templateDto.getObjectsTypeId(), templateDto.getReportingDocumentId(), templateDto.getSubsectionTemplates()));
    }

    private List<SubsectionTemplate> save(Long objectsTypeId, Long reportingDocumentId, List<NewSubsectionTemplateDto> subsectionTemplates) {
        ObjectsTypeDto objectsType = client.getObjectsType(objectsTypeId);
        List<Double> subsectionTemplatesDb = repository.findAllByObjectsTypeIdAndReportingDocumentId(objectsTypeId, reportingDocumentId)
                                                                            .stream()
                                                                            .map(SubsectionTemplate::getSequentialSubsectionNumber)
                                                                            .toList();
        if (!subsectionTemplatesDb.isEmpty()) {
            subsectionTemplates = subsectionTemplates.stream()
                                       .filter(s -> !subsectionTemplatesDb.contains(s.getSequentialSubsectionNumber()))
                                       .toList();
        }
        return repository.saveAll(subsectionTemplates.stream()
                .map(t -> {
                    SubsectionTemplate template = mapper.mapToNewSubsectionTemplate(t);
                    template.setSubsectionDataType(convert.convertSubsectionDataType(t.getSubsectionDataType()));
                    template.setObjectsTypeId(objectsTypeId);
                    template.setReportingDocumentId(reportingDocumentId));
                    subsectionDataService.get(template, objectsType, t.getDivisionType(), t.getDivisionId());
                    return template;
                })
                .toList());
    }
}