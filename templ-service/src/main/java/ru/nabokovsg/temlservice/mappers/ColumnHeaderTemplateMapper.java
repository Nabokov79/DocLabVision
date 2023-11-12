package ru.nabokovsg.temlservice.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.temlservice.dto.table.NewColumnHeaderTemplateDto;
import ru.nabokovsg.temlservice.models.ColumnHeaderTemplate;

@Mapper(componentModel = "spring")
public interface ColumnHeaderTemplateMapper {

    ColumnHeaderTemplate mapToNewColumnHeaderTemplates(NewColumnHeaderTemplateDto templateDto);
}