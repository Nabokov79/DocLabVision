package ru.nabokovsg.temlservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.client.TemplateClient;
import ru.nabokovsg.temlservice.dto.client.ReportingDocumentDto;
import ru.nabokovsg.temlservice.dto.header.NewHeaderTemplateDto;
import ru.nabokovsg.temlservice.exceptions.NotFoundException;
import ru.nabokovsg.temlservice.mappers.ProtocolTemplateMapper;
import ru.nabokovsg.temlservice.models.HeaderTemplate;
import ru.nabokovsg.temlservice.models.ProtocolTemplate;
import ru.nabokovsg.temlservice.repository.ProtocolTemplateRepository;
import ru.nabokovsg.temlservice.dto.protocol.ProtocolTemplateDto;

@Service
@RequiredArgsConstructor
public class ProtocolTemplateServiceImpl implements ProtocolTemplateService {

    private final ProtocolTemplateRepository repository;
    private final ProtocolTemplateMapper mapper;
    private final TemplateClient client;
    @Override
    public ProtocolTemplateDto save(HeaderTemplate header, NewHeaderTemplateDto headerDto) {
        ProtocolTemplate protocol = new ProtocolTemplate();
        ReportingDocumentDto document = client.getReportingDocument(headerDto.getReportingDocumentId());
        protocol.setObjectsTypeId(headerDto.getObjectsTypeId());
        protocol.setReportingDocumentId(headerDto.getReportingDocumentId());
        protocol.setProtocolType(document.getProtocolType());
        protocol.setProtocolName(document.getDocument());
        protocol.setProtocolTitle(document.getDocumentTitle());
        protocol.setProtocolType(document.getProtocolType());
        protocol.setHeader(header);
        return mapper.mapToProtocolTemplateDto(repository.save(protocol));
    }

    @Override
    public ProtocolTemplateDto get(Long objectsTypeId, Long reportingDocumentId) {
        ProtocolTemplate protocol = getById(objectsTypeId, reportingDocumentId);
        if (protocol == null) {
            throw new NotFoundException(
                    String.format("Protocol template by objectsTypeId=%s, reportingDocumentId=%s not found"
                            , objectsTypeId
                            , reportingDocumentId));
        }
        return mapper.mapToProtocolTemplateDto(protocol);
    }

    @Override
    public ProtocolTemplate getById(Long objectsTypeId, Long reportingDocumentId) {
        return repository.findByObjectsTypeIdAndReportingDocumentId(objectsTypeId, reportingDocumentId);
    }
}