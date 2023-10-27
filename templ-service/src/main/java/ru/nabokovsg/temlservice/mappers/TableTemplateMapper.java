package ru.nabokovsg.temlservice.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.temlservice.dto.tableTemlate.NewTableTemplateDto;
import ru.nabokovsg.temlservice.dto.tableTemlate.TableTemplateDto;
import ru.nabokovsg.temlservice.models.TableTemplate;

@Mapper(componentModel = "spring")
public interface TableTemplateMapper {

    TableTemplate mapToNewTableTemplate(NewTableTemplateDto tableTemplateDto);

    TableTemplateDto mapToTableTemplateDto(TableTemplate tableTemplate);
}