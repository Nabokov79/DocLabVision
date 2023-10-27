package ru.nabokovsg.dataservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.objectPassportDataTemplate.NewObjectPassportDataTemplateDto;
import ru.nabokovsg.dataservice.dto.objectPassportDataTemplate.ObjectPassportDataTemplateDto;
import ru.nabokovsg.dataservice.dto.objectPassportDataTemplate.UpdateObjectPassportDataTemplateDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mappers.ObjectPassportDataTemplateMapper;
import ru.nabokovsg.dataservice.models.ObjectPassportDataTemplate;
import ru.nabokovsg.dataservice.models.ObjectsType;
import ru.nabokovsg.dataservice.repository.ObjectPassportDataTemplateRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ObjectPassportDataTemplateServiceImpl implements ObjectPassportDataTemplateService {

    private final ObjectPassportDataTemplateRepository repository;
    private final ObjectPassportDataTemplateMapper mapper;
    private final ObjectsTypeService objectsTypeService;

    @Override
    public List<ObjectPassportDataTemplateDto> save(List<Long> objectsTypeId
                                                                , List<NewObjectPassportDataTemplateDto> templatesDto) {
        List<ObjectPassportDataTemplate> templates = mapper.mapToNewObjectPassportDataTemplate(templatesDto);
        List<ObjectsType> objectsTypes = objectsTypeService.getAll(objectsTypeId);
        List<ObjectPassportDataTemplate> templatesDb = new ArrayList<>();
        for (ObjectsType type : objectsTypes) {
            templatesDb.addAll(templates.stream().peek(t -> t.setObjectsType(type)).toList());
        }
        return mapper.mapToObjectPassportDataTemplateDto(repository.saveAll(templatesDb));
    }

    @Override
    public List<ObjectPassportDataTemplateDto> update(List<UpdateObjectPassportDataTemplateDto> templatesDto) {
        validateIds(templatesDto.stream().map(UpdateObjectPassportDataTemplateDto::getId).toList());
        return mapper.mapToObjectPassportDataTemplateDto(
                repository.saveAll(mapper.mapToUpdateObjectPassportDataTemplate(templatesDto)));
    }

    private void validateIds(List<Long> ids) {
        Map<Long, ObjectPassportDataTemplate> templates = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(ObjectPassportDataTemplate::getId, m -> m));
        if (templates.size() != ids.size() || templates.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(templates.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("templates with ids= %s not found", ids));
        }
    }
}