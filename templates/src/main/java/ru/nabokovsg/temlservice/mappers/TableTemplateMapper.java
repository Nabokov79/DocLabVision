package ru.nabokovsg.temlservice.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.temlservice.dto.table.NewProtocolTableTemplateDto;
import ru.nabokovsg.temlservice.dto.table.NewSubsectionTableTemplateDto;
import ru.nabokovsg.temlservice.models.TableTemplate;

@Mapper(componentModel = "spring")
public interface TableTemplateMapper {

    TableTemplate mapToNewSubsectionTableTemplate(NewSubsectionTableTemplateDto tableTemplateDto);

    TableTemplate mapToNewProtocolTableTemplate(NewProtocolTableTemplateDto tableTemplateDto);
}