package ru.nabokovsg.temlservice.services;

import ru.nabokovsg.temlservice.dto.protocol.NewProtocolTemplateDto;
import ru.nabokovsg.temlservice.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.temlservice.models.ProtocolTemplate;

import java.util.List;
import java.util.Set;

public interface ProtocolTemplateService {

    ProtocolTemplateDto save(NewProtocolTemplateDto protocolTemplateDto);

    Set<ProtocolTemplate> getAllByIds(Long objectsTypeId, Long reportingDocumentId);

    void saveAll(Set<ProtocolTemplate> protocolTemplates);
}