package ru.nabokovsg.temlservice.services;

import ru.nabokovsg.temlservice.dto.header.NewHeaderTemplateDto;
import ru.nabokovsg.temlservice.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.temlservice.models.HeaderTemplate;
import ru.nabokovsg.temlservice.models.ProtocolTemplate;

public interface ProtocolTemplateService {

    ProtocolTemplateDto save(HeaderTemplate header, NewHeaderTemplateDto headerDto);

    ProtocolTemplateDto get(Long objectsTypeId, Long reportingDocumentId);

    ProtocolTemplate getProtocolById(Long id);

    ProtocolTemplate getById(Long objectsTypeId, Long reportingDocumentId);

    ProtocolTemplate saveProtocol(ProtocolTemplate protocol);
}