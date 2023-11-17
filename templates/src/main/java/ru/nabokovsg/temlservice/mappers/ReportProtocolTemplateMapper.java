package ru.nabokovsg.temlservice.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.nabokovsg.temlservice.dto.client.ReportingDocumentDto;
import ru.nabokovsg.temlservice.dto.reportProtocol.NewReportProtocolTemplateDto;
import ru.nabokovsg.temlservice.dto.reportProtocol.ReportProtocolTemplateDto;
import ru.nabokovsg.temlservice.dto.subsection.NewReportProtocolSubsectionTemplateDto;
import ru.nabokovsg.temlservice.models.ReportProtocolTemplate;
import ru.nabokovsg.temlservice.models.SubsectionTemplate;

@Mapper(componentModel = "spring")
public interface ReportProtocolTemplateMapper {

    @Mappings({
            @Mapping(source = "documentDto.protocolType", target = "protocolType"),
            @Mapping(source = "protocolDto.sequentialProtocolNumber", target = "sequentialProtocolNumber"),
            @Mapping(source = "documentDto.document", target = "protocolName"),
            @Mapping(source = "documentDto.documentTitle", target = "protocolTitle"),
            @Mapping(source = "protocolDto.protocolText", target = "protocolText"),
            @Mapping(target = "id", ignore = true)
    })
    ReportProtocolTemplate mapToNewReportProtocolTemplate(NewReportProtocolTemplateDto protocolDto
                                                                , ReportingDocumentDto documentDto);

    ReportProtocolTemplateDto mapToReportProtocolTemplate(ReportProtocolTemplate protocol);

    SubsectionTemplate mapToNewReportProtocolSubsectionTemplate(NewReportProtocolSubsectionTemplateDto protocolDto);
}