package ru.nabokovsg.temlservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.dto.tableTemlate.NewColumnHeaderTemplateDto;
import ru.nabokovsg.temlservice.mappers.ColumnHeaderTemplateMapper;
import ru.nabokovsg.temlservice.models.ColumnHeaderTemplate;
import ru.nabokovsg.temlservice.repository.ColumnHeaderTemplateRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ColumnHeaderTemplateServiceImpl implements ColumnHeaderTemplateService {

    private final ColumnHeaderTemplateRepository repository;
    private final ColumnHeaderTemplateMapper mapper;
    private final ConvertStringToEnumService convert;

    @Override
    public List<ColumnHeaderTemplate> save(List<NewColumnHeaderTemplateDto> templatesDto) {
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