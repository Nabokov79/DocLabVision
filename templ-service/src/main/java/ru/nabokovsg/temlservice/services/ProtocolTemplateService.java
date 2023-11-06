package ru.nabokovsg.temlservice.services;

import ru.nabokovsg.temlservice.builders.TemplateData;
import ru.nabokovsg.temlservice.dto.protocol.NewProtocolTemplateDto;
import ru.nabokovsg.temlservice.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.temlservice.models.ProtocolTemplate;
import ru.nabokovsg.temlservice.models.ReportTemplate;

public interface ProtocolTemplateService {

    ProtocolTemplateDto save(NewProtocolTemplateDto protocolTemplateDto);

    ProtocolTemplateDto saveProtocolTemplate(ProtocolTemplate protocol);

    ProtocolTemplate getById(Long objectsTypeId, Long reportingDocumentId);
}