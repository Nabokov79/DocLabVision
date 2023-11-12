package ru.nabokovsg.temlservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.client.TemplateClient;
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
        return mapper.mapToProtocolTemplateDto(
               saveProtocol(mapper.mapToNewProtocolTemplate(header
                                                       , client.getReportingDocument(headerDto.getReportingDocumentId())
                                                       , headerDto.getObjectsTypeId()
                                                       , headerDto.getReportingDocumentId()))
        );
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
    public ProtocolTemplate getProtocolById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Protocol template with id=%s not found", id)));
    }

    @Override
    public ProtocolTemplate getById(Long objectsTypeId, Long reportingDocumentId) {
        return repository.findByObjectsTypeIdAndReportingDocumentId(objectsTypeId, reportingDocumentId);
    }

    @Override
    public ProtocolTemplate saveProtocol(ProtocolTemplate protocol) {
        return repository.save(protocol);
    }
}