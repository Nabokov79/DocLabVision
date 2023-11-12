package ru.nabokovsg.temlservice.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.temlservice.dto.client.ReportingDocumentDto;
import ru.nabokovsg.temlservice.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.temlservice.models.HeaderTemplate;
import ru.nabokovsg.temlservice.models.ProtocolTemplate;

@Mapper(componentModel = "spring")
public interface ProtocolTemplateMapper {

    @Mapping(source = "documentDto.protocolType", target = "protocolType")
    @Mapping(source = "documentDto.document", target = "protocolName")
    @Mapping(source = "documentDto.documentTitle", target = "protocolTitle")
    @Mapping(source = "objectsTypeId", target = "objectsTypeId")
    @Mapping(source = "reportingDocumentId", target = "reportingDocumentId")
    @Mapping(source = "header", target = "header")
    @Mapping(target = "id", ignore = true)
    ProtocolTemplate mapToNewProtocolTemplate(HeaderTemplate header
                                            , ReportingDocumentDto documentDto
                                            , Long objectsTypeId
                                            , Long reportingDocumentId);

    ProtocolTemplateDto mapToProtocolTemplateDto(ProtocolTemplate protocolTemplate);
}