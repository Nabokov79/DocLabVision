package ru.nabokovsg.temlservice.services.tables;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.dto.table.NewColumnHeaderTemplateDto;
import ru.nabokovsg.temlservice.models.*;
import ru.nabokovsg.temlservice.repository.TableTemplateRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TableTemplateServiceImpl implements TableTemplateService {

    private final TableTemplateRepository repository;
    private final ColumnHeaderTemplateService columnHeaderService;

    public TableTemplate save(TableTemplate table, List<NewColumnHeaderTemplateDto> columnHeaders) {
        table.setColumnsHeaders(columnHeaderService.save(columnHeaders));
        return repository.save(table);
    }
}