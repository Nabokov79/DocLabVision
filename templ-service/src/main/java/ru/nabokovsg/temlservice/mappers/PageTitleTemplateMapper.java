package ru.nabokovsg.temlservice.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import ru.nabokovsg.temlservice.dto.pageTitle.NewPageTitleTemplateDto;
import ru.nabokovsg.temlservice.models.HeaderTemplate;
import ru.nabokovsg.temlservice.models.PageTitleTemplate;

@Mapper(componentModel = "spring")
public interface PageTitleTemplateMapper {

    PageTitleTemplate mapToNewPageTitleTemplate(NewPageTitleTemplateDto pageTitleTemplateDto);


    @Mappings({
            @Mapping(source = "header" , target = "header"),
            @Mapping(source = "signature" , target = "signatureString"),
            @Mapping(source = "year" , target = "year"),
            @Mapping(source = "documentName" , target = "documentName"),
            @Mapping(source = "documentTitle" , target = "documentTitle"),
            @Mapping( target = "id", ignore = true)
    })
    PageTitleTemplate mapToPageTitleTemplate(@MappingTarget PageTitleTemplate pageTitle
                                                                  , String documentName
                                                                  , String documentTitle
                                                                  , HeaderTemplate header
                                                                  , String signature
                                                                  , String year);
}