package ru.nabokovsg.temlservice.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.temlservice.dto.report.NewReportTemplateDto;
import ru.nabokovsg.temlservice.dto.report.ReportTemplateDto;
import ru.nabokovsg.temlservice.models.ReportTemplate;

@Mapper(componentModel = "spring")
public interface ReportTemplateMapper {

    ReportTemplate mapToNewReportTemplateDto(NewReportTemplateDto reportTemplateDto);

    ReportTemplateDto mapToReportTemplateDto(ReportTemplate reportTemplate);
}