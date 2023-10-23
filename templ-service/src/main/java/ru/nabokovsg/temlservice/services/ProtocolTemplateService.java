package ru.nabokovsg.temlservice.services;

import ru.nabokovsg.temlservice.dto.protocol.NewProtocolTemplateDto;
import ru.nabokovsg.temlservice.dto.protocol.ProtocolTemplateDto;

public interface ProtocolTemplateService {

    ProtocolTemplateDto save(NewProtocolTemplateDto templateDto);
}