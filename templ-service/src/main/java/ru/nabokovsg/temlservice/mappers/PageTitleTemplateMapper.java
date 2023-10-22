package ru.nabokovsg.temlservice.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.temlservice.dto.pageTitle.PageTitleTemplateDto;
import ru.nabokovsg.temlservice.models.PageTitleTemplate;

@Mapper(componentModel = "spring")
public interface PageTitleTemplateMapper {

    PageTitleTemplateDto mapToPageTitleTemplateDto(PageTitleTemplate pageTitleTemplate);
}
