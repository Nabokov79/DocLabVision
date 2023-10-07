package ru.nabokovsg.dataservice.services;

import ru.nabokovsg.dataservice.dto.objectsType.NewObjectsTypeDto;
import ru.nabokovsg.dataservice.dto.objectsType.ObjectsTypeDto;
import ru.nabokovsg.dataservice.dto.objectsType.UpdateObjectsTypeDto;
import ru.nabokovsg.dataservice.models.ObjectsType;

import java.util.List;

public interface ObjectsTypeService {

    List<ObjectsTypeDto> save(List<NewObjectsTypeDto> objectsTypeDto);

    List<ObjectsTypeDto> update(List<UpdateObjectsTypeDto> objectsTypeDto);

    List<ObjectsType> getAll(List<Long> ids);

    void delete(Long id);
}