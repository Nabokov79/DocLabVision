package ru.nabokovsg.dataservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.objectsType.*;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mappers.ObjectsTypeMapper;
import ru.nabokovsg.dataservice.models.*;
import ru.nabokovsg.dataservice.repository.ObjectsTypeRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ObjectsTypeServiceImpl implements ObjectsTypeService {

    private final ObjectsTypeRepository repository;
    private final ObjectsTypeMapper mapper;

    @Override
    public List<ShortObjectsTypeDto> save(List<NewObjectsTypeDto> objectsTypeDto) {
        List<ObjectsType> objectsTypesDb = repository.findAll();
        List<NewObjectsTypeDto> objectTypes = mapper.mapToNewObjectTypeDto(objectsTypesDb);
        List<ObjectsType> objectsTypes = objectsTypeDto.stream()
                                                       .filter(o -> !objectTypes.contains(o))
                                                       .distinct()
                                                       .map(mapper::mapToNewObjectType)
                                                       .toList();
        if (objectsTypes.isEmpty()) {
            return mapper.mapToShortObjectsTypeDto(objectsTypesDb);
        }
        if (objectsTypes.size() != objectsTypeDto.size()) {
            objectsTypesDb.addAll(repository.saveAll(objectsTypes));
            return mapper.mapToShortObjectsTypeDto(objectsTypesDb);
        } else {
            return mapper.mapToShortObjectsTypeDto(repository.saveAll(objectsTypes));
        }
    }

    @Override
    public List<ShortObjectsTypeDto> update(List<UpdateObjectsTypeDto> objectsTypeDto) {
        validateByIds(objectsTypeDto.stream().map(UpdateObjectsTypeDto::getId).toList());
        List<ObjectsType> objectsTypes = objectsTypeDto.stream().map(mapper::mapToUpdateObjectType).toList();
        return mapper.mapToShortObjectsTypeDto(repository.saveAll(objectsTypes));
    }

    @Override
    public ObjectsTypeDto get(Long id) {
        return mapper.mapToObjectTypeDto(getById(id));
    }

    @Override
    public ObjectsType getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Objects type with id=%s not found", id)));
    }

    @Override
    public List<ObjectsTypeDocumentationDto> addDocumentations(List<Long> ids, List<Documentation> documentations) {
        return repository.saveAll(getAllByIds(ids).stream()
                                                  .peek(o -> o.setDocumentations(documentations))
                                                  .toList())
                                            .stream()
                                            .map(mapper::mapToObjectsTypeDocumentationDto)
                                            .toList();
    }

    @Override
    public List<ObjectsTypeElementsDto> addElements(List<Long> ids, List<Element> elements) {
        return mapper.mapToObjectsTypeElementsDto(repository.saveAll(getAllByIds(ids)
                                                            .stream()
                                                            .peek(o -> o.getElements().addAll(elements.stream()
                                                                    .filter(e -> e.getObjectsTypeId().equals(o.getId()))
                                                                    .toList()))
                                                            .toList()));
    }

    @Override
    public List<ObjectsTypeDefectDto> addDefects(List<Long> ids, List<Defect> defects) {
        return repository.saveAll(getAllByIds(ids).stream()
                                                  .peek(o -> o.setDefects(defects))
                                                  .toList())
                                            .stream()
                                            .map(mapper::mapToObjectsTypeDefectDto)
                                            .toList();
    }

    @Override
    public List<ObjectsTypeRepairMethodDto> addRepairMethods(List<Long> ids, List<RepairMethod> methods) {
        return repository.saveAll(getAllByIds(ids).stream()
                                                  .peek(o -> o.setRepairMethods(methods))
                                                  .toList())
                                            .stream()
                                            .map(mapper::mapToObjectsTypeRepairMethodDto)
                                            .toList();
    }

    @Override
    public List<ObjectsTypePassportDataTemplateDto> addObjectPassportDataTemplates(List<Long> ids
                                                                         , List<ObjectPassportDataTemplate> templates) {
        return repository.saveAll(getAllByIds(ids).stream()
                                                  .peek(o -> o.setDataTemplates(templates))
                                                  .toList())
                                            .stream()
                                            .map(mapper::mapToObjectsTypePassportDataTemplateDto)
                                            .toList();
    }

    @Override
    public List<ObjectsTypeNormDto> addNorms(List<Long> ids, List<Norm> norms) {
        return repository.saveAll(getAllByIds(ids).stream()
                                                    .peek(o -> o.setNorms(norms))
                                                    .toList())
                                            .stream()
                                            .map(mapper::mapToObjectsTypeNormDto)
                                            .toList();
    }

    private List<ObjectsType> getAllByIds(List<Long> ids) {
        List<ObjectsType> objectsTypes = repository.findAllById(ids);
        if (objectsTypes.isEmpty()) {
            throw new NotFoundException(String.format("ObjectsType with ids=%s not found", ids));
        }
        return objectsTypes;
    }


    @Override
    public List<ObjectsType> getAll(List<Long> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("measuring tool with id = %s not found for delete", id));
    }

    private void validateByIds(List<Long> ids) {
        Map<Long, ObjectsType> objectsTypes = getAllByIds((ids))
                .stream().collect(Collectors.toMap(ObjectsType::getId, o -> o));
        if (objectsTypes.size() != ids.size() || objectsTypes.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(objectsTypes.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("objects types with ids= %s not found", ids));
        }
    }
}