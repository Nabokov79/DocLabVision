package ru.nabokovsg.temlservice.services.report;

import ru.nabokovsg.temlservice.dto.report.NewReportTemplateDto;
import ru.nabokovsg.temlservice.dto.report.ReportTemplateDto;
import ru.nabokovsg.temlservice.models.ReportTemplate;

public interface ReportTemplateService {

    ReportTemplateDto save(NewReportTemplateDto reportTemplateDto);

    ReportTemplateDto get(Long id, Long objectsTypeId, Long reportingDocumentId);

    ReportTemplate getById(Long objectsTypeId, Long reportingDocumentId);

    ReportTemplateDto saveReportTemplate(ReportTemplate reportTemplate);
}