package ru.nabokovsg.temlservice.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.temlservice.dto.header.NewHeaderTemplateDto;
import ru.nabokovsg.temlservice.dto.header.NewPageTitleHeaderTemplateDto;

@Mapper(componentModel = "spring")
public interface HeaderTemplateMapper {

    NewHeaderTemplateDto mapToNewHeaderTemplateDto(NewPageTitleHeaderTemplateDto pageTitleDto);
}
