package ru.nabokovsg.temlservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.dto.table.NewColumnHeaderTemplateDto;
import ru.nabokovsg.temlservice.mappers.ColumnHeaderTemplateMapper;
import ru.nabokovsg.temlservice.models.ColumnHeaderTemplate;
import ru.nabokovsg.temlservice.repository.ColumnHeaderTemplateRepository;
import ru.nabokovsg.temlservice.services.converter.ConvertToEnumService;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ColumnHeaderTemplateServiceImpl implements ColumnHeaderTemplateService {

    private final ColumnHeaderTemplateRepository repository;
    private final ColumnHeaderTemplateMapper mapper;
    private final ConvertToEnumService convert;

    @Override
    public List<ColumnHeaderTemplate> save(List<NewColumnHeaderTemplateDto> templatesDto) {
        Map<String, ColumnHeaderTemplate> columnHeadersDb = repository.findAllByCellName(templatesDto
                                                                           .stream()
                                                                           .map(NewColumnHeaderTemplateDto::getCellName)
                                                                           .toList())
                                                .stream()
                                                .collect(Collectors.toMap(ColumnHeaderTemplate::getCellName, c -> c));
        if (!columnHeadersDb.isEmpty()) {
            templatesDto = templatesDto.stream()
                                       .filter(s -> !columnHeadersDb.containsKey(s.getCellName()))
                                       .toList();
            if (templatesDto.isEmpty()) {
                return new ArrayList<>(columnHeadersDb.values());
            } else {
                return Stream.of(columnHeadersDb.values()
                                , saveAll(templatesDto))
                        .flatMap(Collection::stream)
                        .toList();
            }
        } else {
            return saveAll(templatesDto);
        }
    }

    private List<ColumnHeaderTemplate> saveAll(List<NewColumnHeaderTemplateDto> templatesDto) {
        return repository.saveAll(templatesDto
                .stream()
                .map(t -> {
                    ColumnHeaderTemplate template = mapper.mapToNewColumnHeaderTemplates(t);
                    template.setColumnDataType(convert.covertColumnDataType(t.getColumnDataType()));
                    return template;
                })
                .toList());
    }
}