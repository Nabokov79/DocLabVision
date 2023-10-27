package ru.nabokovsg.temlservice.services;

import ru.nabokovsg.temlservice.dto.pageTitle.NewPageTitleTemplateDto;
import ru.nabokovsg.temlservice.models.PageTitleTemplate;

public interface PageTitleTemplateService {

    PageTitleTemplate save(Long reportingDocumentId, NewPageTitleTemplateDto pageTitleTemplateDto);
}
