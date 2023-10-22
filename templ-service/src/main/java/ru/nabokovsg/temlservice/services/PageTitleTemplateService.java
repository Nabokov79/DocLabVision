package ru.nabokovsg.temlservice.services;

import ru.nabokovsg.temlservice.dto.pageTitle.NewPageTitleTemplateDto;
import ru.nabokovsg.temlservice.dto.pageTitle.PageTitleTemplateDto;

public interface PageTitleTemplateService {

    PageTitleTemplateDto save(NewPageTitleTemplateDto pageTitleDto);
}