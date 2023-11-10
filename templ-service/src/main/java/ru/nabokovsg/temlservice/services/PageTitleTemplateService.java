package ru.nabokovsg.temlservice.services;

import ru.nabokovsg.temlservice.dto.pageTitle.NewPageTitleTemplateDto;
import ru.nabokovsg.temlservice.dto.report.ReportTemplateDto;

public interface PageTitleTemplateService {

   ReportTemplateDto save(NewPageTitleTemplateDto pageTitleDto);
}