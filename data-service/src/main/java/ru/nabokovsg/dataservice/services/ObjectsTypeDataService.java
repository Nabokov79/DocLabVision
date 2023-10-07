package ru.nabokovsg.dataservice.services;

import ru.nabokovsg.dataservice.dto.objectsTypeData.ObjectsTypeDataDto;
import ru.nabokovsg.dataservice.models.DataBuilder;
import ru.nabokovsg.dataservice.models.ObjectsTypeData;

import java.util.List;

public interface ObjectsTypeDataService {

    List<ObjectsTypeData> save(DataBuilder builder);

    ObjectsTypeDataDto get(Long id);
}