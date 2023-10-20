package ru.nabokovsg.temlservice.services;

import ru.nabokovsg.temlservice.dto.pageTitle.NewPageTitleDto;
import ru.nabokovsg.temlservice.models.PageTitle;

public interface PageTitleService {

    PageTitle save(NewPageTitleDto pageTitleDto);
}