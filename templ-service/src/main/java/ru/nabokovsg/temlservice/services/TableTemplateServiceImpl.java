package ru.nabokovsg.temlservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.dto.tableTemlate.NewTableTemplateDto;
import ru.nabokovsg.temlservice.dto.tableTemlate.TableTemplateDto;
import ru.nabokovsg.temlservice.mappers.TableTemplateMapper;
import ru.nabokovsg.temlservice.models.TableTemplate;
import ru.nabokovsg.temlservice.repository.TableTemplateRepository;

@Service
@RequiredArgsConstructor
public class TableTemplateServiceImpl implements TableTemplateService {

    private final TableTemplateRepository repository;
    private final TableTemplateMapper mapper;
    private final ColumnHeaderTemplateService columnHeaderTemplateService;
    private final ConvertStringToEnumService convert;


    @Override
    public TableTemplateDto save(NewTableTemplateDto templateDto) {
        TableTemplate template = mapper.mapToNewTableTemplate(templateDto);
        template.setTableDataType(convert.convertTableDataType(templateDto.getTableDataType()));
        template.setColumnsHeaders(columnHeaderTemplateService.save(templateDto.getColumnHeaders()));
        return mapper.mapToTableTemplateDto(repository.save(template));
    }
}