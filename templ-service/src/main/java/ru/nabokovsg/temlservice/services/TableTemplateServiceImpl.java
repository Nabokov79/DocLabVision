package ru.nabokovsg.temlservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.builders.TemplateData;
import ru.nabokovsg.temlservice.dto.report.ReportTemplateDto;
import ru.nabokovsg.temlservice.dto.tableTemlate.NewTableTemplateDto;
import ru.nabokovsg.temlservice.enums.DataType;
import ru.nabokovsg.temlservice.mappers.TableTemplateMapper;
import ru.nabokovsg.temlservice.models.ReportTemplate;
import ru.nabokovsg.temlservice.models.TableTemplate;
import ru.nabokovsg.temlservice.repository.TableTemplateRepository;
import ru.nabokovsg.temlservice.services.converters.ConvertToEnumService;
import ru.nabokovsg.temlservice.services.report.ReportTemplateDataService;
import ru.nabokovsg.temlservice.services.report.ReportTemplateService;

@Service
@RequiredArgsConstructor
public class TableTemplateServiceImpl implements TableTemplateService {

    private final TableTemplateRepository repository;
    private final TableTemplateMapper mapper;
    private final ColumnHeaderTemplateService columnHeaderTemplateService;
    private final ConvertToEnumService convert;
    private final ReportTemplateService reportTemplateService;
    private final ReportTemplateDataService service;


    @Override
    public ReportTemplateDto save(NewTableTemplateDto templateDto) {
        ReportTemplate report = reportTemplateService.getById(templateDto.getObjectsTypeId()
                , templateDto.getReportingDocumentId());
        if (report != null) {
            TableTemplate table = mapper.mapToNewTableTemplate(templateDto);
            table.setTableDataType(convert.convertTableDataType(templateDto.getTableDataType()));
            table.setColumnsHeaders(columnHeaderTemplateService.save(templateDto.getColumnHeaders()));
            table = repository.save(table);
            return service.add(new TemplateData.Builder().dataType(DataType.TABLE).report(report).table(table).build());
        }
        return new ReportTemplateDto();
    }
}