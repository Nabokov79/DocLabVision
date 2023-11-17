package ru.nabokovsg.temlservice.services.tables;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.dto.reportProtocol.ReportProtocolTemplateDto;
import ru.nabokovsg.temlservice.dto.table.NewProtocolTableTemplateDto;
import ru.nabokovsg.temlservice.mappers.ReportProtocolTemplateMapper;
import ru.nabokovsg.temlservice.mappers.TableTemplateMapper;
import ru.nabokovsg.temlservice.models.ReportProtocolTemplate;
import ru.nabokovsg.temlservice.models.TableTemplate;
import ru.nabokovsg.temlservice.services.reports.ReportProtocolTemplateService;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportProtocolTableTemplateServiceImpl implements ReportProtocolTableTemplateService {

    private final TableTemplateService tableService;
    private final TableTemplateMapper mapper;
    private final ReportProtocolTemplateService reportProtocolService;
    private final ReportProtocolTemplateMapper reportProtocolMapper;

    @Override
    public ReportProtocolTemplateDto save(NewProtocolTableTemplateDto templateDto) {
        ReportProtocolTemplate protocol = reportProtocolService.get(templateDto.getProtocolId());
        if (protocol != null) {
            Map<String, TableTemplate> tables = protocol.getTables()
                    .stream()
                    .collect(Collectors.toMap(t -> t.getTableDataType().toString(), t -> t));
            if (tables.get(templateDto.getTableDataType()) == null) {
                protocol.getTables().add(tableService.save(mapper.mapToNewProtocolTableTemplate(templateDto)
                        , templateDto.getColumnHeaders()));
            }
            protocol = reportProtocolService.saveProtocolTemplate(protocol);
        }
        return reportProtocolMapper.mapToReportProtocolTemplate(protocol);
    }
}