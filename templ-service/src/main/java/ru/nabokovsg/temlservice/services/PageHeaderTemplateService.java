package ru.nabokovsg.temlservice.services;

import ru.nabokovsg.temlservice.dto.pageHeader.NewPageHeaderTemplateDto;
import ru.nabokovsg.temlservice.models.PageHeaderTemplate;

public interface PageHeaderTemplateService {

    PageHeaderTemplate save(Long reportingDocumentId, NewPageHeaderTemplateDto pageTitleDto);
}