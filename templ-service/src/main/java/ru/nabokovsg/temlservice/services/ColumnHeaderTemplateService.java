package ru.nabokovsg.temlservice.services;

import ru.nabokovsg.temlservice.dto.tableTemlate.NewColumnHeaderTemplateDto;
import ru.nabokovsg.temlservice.models.ColumnHeaderTemplate;

import java.util.List;

public interface ColumnHeaderTemplateService {

    List<ColumnHeaderTemplate> save(List<NewColumnHeaderTemplateDto> templatesDto);
}