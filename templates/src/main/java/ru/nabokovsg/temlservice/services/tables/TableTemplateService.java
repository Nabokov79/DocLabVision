package ru.nabokovsg.temlservice.services.tables;

import ru.nabokovsg.temlservice.dto.table.NewColumnHeaderTemplateDto;
import ru.nabokovsg.temlservice.models.TableTemplate;

import java.util.List;

public interface TableTemplateService {

    TableTemplate save(TableTemplate table, List<NewColumnHeaderTemplateDto> columnHeaders);
}