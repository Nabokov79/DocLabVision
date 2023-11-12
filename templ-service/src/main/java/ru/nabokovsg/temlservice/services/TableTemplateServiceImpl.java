package ru.nabokovsg.temlservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.temlservice.dto.section.SectionTemplateDto;
import ru.nabokovsg.temlservice.dto.table.NewColumnHeaderTemplateDto;
import ru.nabokovsg.temlservice.dto.table.NewProtocolTableTemplateDto;
import ru.nabokovsg.temlservice.dto.table.NewSubsectionTableTemplateDto;
import ru.nabokovsg.temlservice.mappers.ProtocolTemplateMapper;
import ru.nabokovsg.temlservice.mappers.SectionTemplateMapper;
import ru.nabokovsg.temlservice.mappers.TableTemplateMapper;
import ru.nabokovsg.temlservice.models.ProtocolTemplate;
import ru.nabokovsg.temlservice.models.SectionTemplate;
import ru.nabokovsg.temlservice.models.SubsectionTemplate;
import ru.nabokovsg.temlservice.models.TableTemplate;
import ru.nabokovsg.temlservice.repository.TableTemplateRepository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TableTemplateServiceImpl implements TableTemplateService {

    private final TableTemplateRepository repository;
    private final TableTemplateMapper mapper;
    private final ColumnHeaderTemplateService columnHeaderTemplateService;
    private final SectionTemplateMapper sectionMapper;
    private final SectionTemplateService sectionService;
    private final SubsectionTemplateService subsectionService;
    private final ProtocolTemplateService protocolService;
    private final ProtocolTemplateMapper protocolMapper;

    @Override
    public SectionTemplateDto addToSubsectionTemplate(NewSubsectionTableTemplateDto templateDto) {
        SectionTemplate section = sectionService.get(templateDto.getSectionId());
        if (section != null) {
            Map<Long, SubsectionTemplate> subsections = section.getSubsections()
                                                          .stream()
                                                          .collect(Collectors.toMap(SubsectionTemplate::getId, s -> s));
            SubsectionTemplate subsection = subsections.get(templateDto.getSubsectionId());
            if (subsection.getTables().isEmpty()) {
                subsection.getTables().add(save(mapper.mapToNewSubsectionTableTemplate(templateDto)
                                              , templateDto.getColumnHeaders()));
                subsections.put(subsection.getId(), subsectionService.save(subsection));
                section.setSubsections(section.getSubsections()
                        .stream()
                        .map(s -> {
                            if (s.getId() == subsection.getId()) {
                                s = subsection;
                            }
                            return s;
                        })
                        .toList());
            }
        }
        return sectionMapper.mapToSectionTemplateDto(section);
    }

    @Override
    public ProtocolTemplateDto addToProtocolTemplate(NewProtocolTableTemplateDto templateDto) {
        ProtocolTemplate protocol = protocolService.getProtocolById(templateDto.getProtocolId());
        if (protocol != null) {
            protocol.getTables().add(save(mapper.mapToNewProtocolTableTemplate(templateDto)
                    , templateDto.getColumnHeaders()));
        }
        return protocolMapper.mapToProtocolTemplateDto(protocolService.saveProtocol(protocol));
    }

    public TableTemplate save(TableTemplate table, List<NewColumnHeaderTemplateDto> columnHeaders) {
        table.setColumnsHeaders(columnHeaderTemplateService.save(columnHeaders));
        return repository.save(table);
    }
}