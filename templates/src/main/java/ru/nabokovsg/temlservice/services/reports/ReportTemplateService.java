package ru.nabokovsg.temlservice.services.reports;

import ru.nabokovsg.temlservice.dto.pageTitle.NewPageTitleTemplateDto;
import ru.nabokovsg.temlservice.dto.report.ReportTemplateDto;
import ru.nabokovsg.temlservice.models.PageTitleTemplate;
import ru.nabokovsg.temlservice.models.ReportTemplate;

public interface ReportTemplateService {

    ReportTemplateDto save(ReportTemplate report);

    ReportTemplateDto get(Long objectsTypeId, Long reportingDocumentId);

    ReportTemplate getById(Long objectsTypeId, Long reportingDocumentId);

    ReportTemplateDto create(PageTitleTemplate pageTitle, NewPageTitleTemplateDto pageTitleDto);
}