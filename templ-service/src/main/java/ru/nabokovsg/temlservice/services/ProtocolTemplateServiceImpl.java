package ru.nabokovsg.temlservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.client.TemplateClient;
import ru.nabokovsg.temlservice.dto.protocol.NewProtocolTemplateDto;
import ru.nabokovsg.temlservice.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.temlservice.dto.client.ReportingDocumentDto;
import ru.nabokovsg.temlservice.mappers.ProtocolTemplateMapper;
import ru.nabokovsg.temlservice.models.ProtocolTemplate;
import ru.nabokovsg.temlservice.repository.ProtocolTemplateRepository;

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
            return mapper.mapToProtocolTemplateDto(repository.save(template));
        }
        return new ProtocolTemplateDto();
    }

    @Override
    public ProtocolTemplateDto saveProtocolTemplate(ProtocolTemplate protocol) {
        return mapper.mapToProtocolTemplateDto(repository.save(protocol));
    }

    @Override
    public ProtocolTemplate getById(Long objectsTypeId, Long reportingDocumentId) {
        return repository.findByObjectsTypeIdAndReportingDocumentId(objectsTypeId, reportingDocumentId);
    }
}