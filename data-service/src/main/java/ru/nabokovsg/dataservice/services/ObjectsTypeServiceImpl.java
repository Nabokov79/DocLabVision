package ru.nabokovsg.dataservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.objectsType.NewObjectsTypeDto;
import ru.nabokovsg.dataservice.dto.objectsType.ObjectsTypeDto;
import ru.nabokovsg.dataservice.dto.objectsType.UpdateObjectsTypeDto;
import ru.nabokovsg.dataservice.dto.plotOfObject.NewPlotOfObjectDto;
import ru.nabokovsg.dataservice.dto.plotOfObject.UpdatePlotOfObjectDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mappers.ObjectsTypeMapper;
import ru.nabokovsg.dataservice.models.ObjectsType;
import ru.nabokovsg.dataservice.models.PlotOfObject;
import ru.nabokovsg.dataservice.repository.ObjectsTypeRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ObjectsTypeServiceImpl implements ObjectsTypeService {

    private final ObjectsTypeRepository repository;
    private final ObjectsTypeMapper mapper;
    private final PlotOfObjectService plotOfObjectService;

    @Override
    public List<ObjectsTypeDto> save(List<NewObjectsTypeDto> objectsTypeDto) {
        List<PlotOfObject> plotOfObjects = plotOfObjectService.save(objectsTypeDto
                                                                            .stream()
                                                                            .map(NewObjectsTypeDto::getPlotOfObjects)
                                                                            .filter(Objects::nonNull)
                                                                            .flatMap(Collection::stream).toList());
        List<ObjectsType> objectsTypes = new ArrayList<>();
        for (NewObjectsTypeDto objectTypeDto : objectsTypeDto) {
            ObjectsType objectType = mapper.mapToNewObjectsType(objectTypeDto);
            if (objectTypeDto.getPlotOfObjects() != null && !objectTypeDto.getPlotOfObjects().isEmpty()) {
                List<String> plotNames = objectTypeDto.getPlotOfObjects().stream()
                                                                        .map(NewPlotOfObjectDto::getPlotName)
                                                                        .toList();
                objectType.setPlotOfObjects(plotOfObjects.stream()
                                                         .filter(o -> plotNames.contains(o.getPlotName()))
                                                         .toList());
            }
            objectsTypes.add(objectType);
        }
        return mapper.mapToObjectsTypeDto(repository.saveAll(objectsTypes));
    }

    @Override
    public List<ObjectsTypeDto> update(List<UpdateObjectsTypeDto> objectsTypeDto) {
        validateByIds(objectsTypeDto.stream().map(UpdateObjectsTypeDto::getId).toList());
        List<PlotOfObject> plotOfObjects = plotOfObjectService.update(objectsTypeDto
                                                                   .stream()
                                                                   .map(UpdateObjectsTypeDto::getPlotOfObjects)
                                                                   .filter(Objects::nonNull).flatMap(Collection::stream)
                                                                   .toList());
        List<ObjectsType> objectsTypes = new ArrayList<>();
        for (UpdateObjectsTypeDto objectTypeDto : objectsTypeDto) {
            ObjectsType objectType = mapper.mapToUpdateObjectsType(objectTypeDto);
            if (objectTypeDto.getPlotOfObjects() != null && !objectTypeDto.getPlotOfObjects().isEmpty()) {
                List<Long> plotNames = objectTypeDto.getPlotOfObjects().stream()
                                                                      .map(UpdatePlotOfObjectDto::getId)
                                                                      .toList();
                objectType.setPlotOfObjects(plotOfObjects.stream()
                                                         .filter(o -> plotNames.contains(o.getId()))
                                                         .toList());
            }
            objectsTypes.add(objectType);
        }
        return mapper.mapToObjectsTypeDto(repository.saveAll(objectsTypes));
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