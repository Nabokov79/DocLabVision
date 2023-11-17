package ru.nabokovsg.temlservice.services.tables;

import ru.nabokovsg.temlservice.dto.table.NewColumnHeaderTemplateDto;
import ru.nabokovsg.temlservice.models.ColumnHeaderTemplate;
import java.util.List;

public interface ColumnHeaderTemplateService {

    List<ColumnHeaderTemplate> save(List<NewColumnHeaderTemplateDto> templatesDto);
}