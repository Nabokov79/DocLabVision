package ru.nabokovsg.temlservice.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.temlservice.dto.templates.DocumentTemplateDto;
import ru.nabokovsg.temlservice.dto.templates.NewTemplateDto;
import ru.nabokovsg.temlservice.models.ProtocolTemplate;

@Mapper(componentModel = "spring")
public interface ProtocolTemplateMapper {

    ProtocolTemplate mapToNewProtocolTemplateDto(NewTemplateDto reportTemplateDto);

    DocumentTemplateDto mapToDocumentTemplateDto(ProtocolTemplate protocolTemplate);
}