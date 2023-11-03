package ru.nabokovsg.temlservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.client.TemplateClient;
import ru.nabokovsg.temlservice.dto.builders.IdsListBuilder;
import ru.nabokovsg.temlservice.dto.protocol.NewProtocolTemplateDto;
import ru.nabokovsg.temlservice.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.temlservice.dto.client.ReportingDocumentDto;
import ru.nabokovsg.temlservice.mappers.ProtocolTemplateMapper;
import ru.nabokovsg.temlservice.models.ProtocolTemplate;
import ru.nabokovsg.temlservice.models.SubsectionTemplate;
import ru.nabokovsg.temlservice.repository.ProtocolTemplateRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProtocolTemplateServiceImpl implements ProtocolTemplateService {

    private final ProtocolTemplateRepository repository;
    private final ProtocolTemplateMapper mapper;
    private final PageHeaderTemplateService pageHeaderTemplateService;
    private final TemplateClient client;

    @Override
    public ProtocolTemplateDto save(NewProtocolTemplateDto protocolTemplateDto) {
        if (!repository.existsByObjectsTypeIdAndReportingDocumentId(protocolTemplateDto.getObjectsTypeId()
                                                                , protocolTemplateDto.getReportingDocumentId())) {
            ReportingDocumentDto reportingDocument = client.getReportingDocument(
                                                                            protocolTemplateDto.getReportingDocumentId()
                                                                    );
            ProtocolTemplate template = mapper.mapToNewProtocolTemplate(protocolTemplateDto);
            template.setPageHeader(pageHeaderTemplateService.save(protocolTemplateDto.getReportingDocumentId()
                                                                , protocolTemplateDto.getPageHeader()));
            template.setProtocolName(reportingDocument.getDocument().toUpperCase());
            template.setProtocolTitle(reportingDocument.getDocumentTitle());
            template.setProtocolType(reportingDocument.getProtocolType());
            return mapper.mapToDocumentTemplateDto(repository.save(template));
        }
        return new ProtocolTemplateDto();
    }

    @Override
    public ProtocolTemplateDto addSubsectionTemplate(IdsListBuilder builder
                                                   , List<SubsectionTemplate> subsectionTemplate) {
        ProtocolTemplate protocolTemplate =
                repository.findByObjectsTypeIdAndReportingDocumentId(builder.getObjectTypeId()
                                                                   , builder.getReportingDocumentId());
        if (protocolTemplate != null) {
           protocolTemplate.getSubsectionsTemplate().addAll(subsectionTemplate);
            return mapper.mapToDocumentTemplateDto(repository.save(protocolTemplate));
        }
        return new ProtocolTemplateDto();
    }
}