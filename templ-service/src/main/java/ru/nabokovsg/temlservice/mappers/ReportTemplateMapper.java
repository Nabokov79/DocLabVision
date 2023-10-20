package ru.nabokovsg.temlservice.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.temlservice.dto.templates.DocumentTemplateDto;
import ru.nabokovsg.temlservice.dto.templates.NewTemplateDto;
import ru.nabokovsg.temlservice.models.ReportTemplate;

@Mapper(componentModel = "spring")
public interface ReportTemplateMapper {

    ReportTemplate mapToNewReportTemplateDto(NewTemplateDto reportTemplateDto);

    DocumentTemplateDto mapToDocumentTemplateDto(ReportTemplate reportTemplate);
}