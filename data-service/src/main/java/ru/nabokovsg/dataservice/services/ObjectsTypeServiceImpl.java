package ru.nabokovsg.dataservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.objectsType.NewObjectsTypeDto;
import ru.nabokovsg.dataservice.dto.objectsType.UpdateObjectsTypeDto;
import ru.nabokovsg.dataservice.dto.objectsType.ObjectsTypeDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mappers.ObjectsTypeMapper;
import ru.nabokovsg.dataservice.models.ObjectsType;
import ru.nabokovsg.dataservice.repository.ObjectsTypeRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ObjectsTypeServiceImpl implements ObjectsTypeService {

    private final ObjectsTypeRepository repository;
    private final ObjectsTypeMapper mapper;

    @Override
    public List<ObjectsTypeDto> save(List<NewObjectsTypeDto> objectsTypeDto) {
        List<ObjectsType> objectsTypes = objectsTypeDto.stream().map(mapper::mapToNewObjectType).toList();
        Set<ObjectsType> objectsTypesDb = new HashSet<>();
//        Set<ObjectsType> objectsTypesDb = repository.findAllByObjectsType(objectsTypes);
        if (objectsTypesDb.isEmpty()) {
            return repository.saveAll(objectsTypes).stream().map(mapper::mapToObjectTypeDto).toList();
        } else {
            return repository.saveAll(objectsTypes.stream()
                                                  .filter(o -> !objectsTypesDb.contains(o))
                                                  .toList()).stream()
                                                  .map(mapper::mapToObjectTypeDto)
                                                  .toList();
        }
    }

    @Override
    public List<ObjectsTypeDto> update(List<UpdateObjectsTypeDto> objectsTypeDto) {
        validateByIds(objectsTypeDto.stream().map(UpdateObjectsTypeDto::getId).toList());
        List<ObjectsType> objectsTypes = objectsTypeDto.stream().map(mapper::mapToUpdateObjectType).toList();
        return repository.saveAll(objectsTypes).stream().map(mapper::mapToObjectTypeDto).toList();
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
        Map<Long, ObjectsType> objectsTypes = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(ObjectsType::getId, o -> o));
        if (objectsTypes.size() != ids.size() || objectsTypes.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(objectsTypes.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("objects types with ids= %s not found", ids));
        }
    }
}