package ru.nabokovsg.temlservice.services.tables;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.temlservice.dto.table.NewProtocolTableTemplateDto;
import ru.nabokovsg.temlservice.mappers.ProtocolTemplateMapper;
import ru.nabokovsg.temlservice.mappers.TableTemplateMapper;
import ru.nabokovsg.temlservice.models.ProtocolTemplate;
import ru.nabokovsg.temlservice.models.TableTemplate;
import ru.nabokovsg.temlservice.services.ProtocolTemplateService;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProtocolTableTemplateServiceImpl implements ProtocolTableTemplateService {

    private final TableTemplateMapper mapper;
    private final TableTemplateService tableService;
    private final ProtocolTemplateService protocolService;
    private final ProtocolTemplateMapper protocolMapper;

    @Override
    public ProtocolTemplateDto save(NewProtocolTableTemplateDto templateDto) {
        ProtocolTemplate protocol = protocolService.getProtocolById(templateDto.getProtocolId());
        if (protocol != null) {
            Map<String, TableTemplate> tableTemplates = protocol.getTables()
                                          .stream()
                                          .collect(Collectors.toMap(t -> String.valueOf(t.getTableDataType()), t -> t));
            if (tableTemplates.get(templateDto.getTableDataType()) == null) {
                protocol.getTables().add(tableService.save(mapper.mapToNewProtocolTableTemplate(templateDto)
                        , templateDto.getColumnHeaders()));
                protocol = protocolService.saveProtocol(protocol);
            }
        }
        return protocolMapper.mapToProtocolTemplateDto(protocol);
    }
}
