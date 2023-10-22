package ru.nabokovsg.temlservice.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.temlservice.dto.protocol.NewProtocolTemplateDto;
import ru.nabokovsg.temlservice.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.temlservice.models.ProtocolTemplate;

@Mapper(componentModel = "spring")
public interface ProtocolTemplateMapper {

    ProtocolTemplate mapToNewProtocolTemplate((NewProtocolTemplateDto reportTemplateDto);

    ProtocolTemplateDto mapToDocumentTemplateDto(ProtocolTemplate protocolTemplate);
}