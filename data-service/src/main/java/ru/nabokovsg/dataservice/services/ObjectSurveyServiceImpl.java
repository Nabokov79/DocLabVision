package ru.nabokovsg.dataservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.ObjectsIds;
import ru.nabokovsg.dataservice.dto.objectSurvey.NewObjectSurveyDto;
import ru.nabokovsg.dataservice.dto.objectSurvey.ShortObjectSurveyDto;
import ru.nabokovsg.dataservice.dto.objectSurvey.ObjectSurveyDto;
import ru.nabokovsg.dataservice.dto.objectSurvey.UpdateObjectSurveyDto;
import ru.nabokovsg.dataservice.enums.BuilderType;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mappers.ObjectSurveyMapper;
import ru.nabokovsg.dataservice.models.*;
import ru.nabokovsg.dataservice.repository.ObjectSurveyRepository;
import ru.nabokovsg.dataservice.services.builder.DataFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ObjectSurveyServiceImpl implements ObjectSurveyService {

    private final ObjectSurveyRepository repository;
    private final ObjectSurveyMapper mapper;
    private final DataFactory factory;

    @Override
    public List<ShortObjectSurveyDto> save(List<NewObjectSurveyDto> objectsDto) {
        DataBuilder builder = getData(mapper.mapFromNewObjectSurveyIds(objectsDto));
        Map<Long, Building> buildings = convertBuildings(builder);
        Map<Long, ObjectsType> types = convertObjectsType(builder);
        return mapper.mapToObjectSurveyDtos(repository.saveAll(
                                          objectsDto.stream()
                                                    .map(o -> {
                                                                SurveyObject object = mapper.mapToNewObjectSurvey(o);
                                                                object.setBuilding(buildings.get(o.getBuildingId()));
                                                                object.setObjectsType(types.get(o.getObjectsTypeId()));
                                                                return object;
                                                            })
                                                    .toList()));
    }

    @Override
    public List<ShortObjectSurveyDto> update(List<UpdateObjectSurveyDto> objectsDto) {
        validateIds(objectsDto.stream().map(UpdateObjectSurveyDto::getId).toList());
        DataBuilder builder = getData(mapper.mapFromUpdateObjectSurveyIds(objectsDto));
        Map<Long, Building> buildings = convertBuildings(builder);
        Map<Long, ObjectsType> types = convertObjectsType(builder);
        return mapper.mapToObjectSurveyDtos(repository.saveAll(
                objectsDto.stream()
                        .map(o -> {
                            SurveyObject object = mapper.mapToUpdateObjectSurvey(o);
                            object.setBuilding(buildings.get(o.getBuildingId()));
                            object.setObjectsType(types.get(o.getObjectsTypeId()));
                            return object;
                        })
                        .toList()));
    }

    @Override
    public ObjectSurveyDto get(Long id) {
        return mapper.mapToObjectSurveyDto(
                repository.findById(id)
                        .orElseThrow(
                                () -> new NotFoundException(String.format("ObjectSurvey with id=%s not found", id)))
        );
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("objects with id= %s not found for delete", id));
    }

    private void validateIds(List<Long> ids) {
        Map<Long, SurveyObject> objects = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(SurveyObject::getId, n -> n));
        if (objects.size() != ids.size() || objects.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(objects.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("ObjectSurvey with ids= %s not found", ids));
        }
    }

    private DataBuilder getData(List<ObjectsIds> ids) {
        return factory.getBuilder(ids, BuilderType.OBJECT);
    }

    private Map<Long, Building> convertBuildings(DataBuilder builder) {
        return builder.getBuildings().stream().collect(Collectors.toMap(Building::getId, b -> b));
    }

    private Map<Long, ObjectsType> convertObjectsType(DataBuilder builder) {
        return builder.getObjectsTypes().stream().collect(Collectors.toMap(ObjectsType::getId, t -> t));
    }
}