package ru.nabokovsg.temlservice.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.temlservice.dto.report.ReportTemplateDto;
import ru.nabokovsg.temlservice.dto.template.NewTemplateDataDto;
import ru.nabokovsg.temlservice.models.ReportTemplate;

@Mapper(componentModel = "spring")
public interface ReportTemplateMapper {

    ReportTemplate mapToNewReportTemplateDto(NewTemplateDataDto templateDto);

    ReportTemplateDto mapToDocumentTemplateDto(ReportTemplate reportTemplate);
}