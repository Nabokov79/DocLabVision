package ru.nabokovsg.dataservice.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.objectsType.NewObjectsTypeDto;
import ru.nabokovsg.dataservice.dto.objectsType.ObjectsTypeDto;
import ru.nabokovsg.dataservice.dto.objectsType.UpdateObjectsTypeDto;
import ru.nabokovsg.dataservice.models.ObjectsType;

@Mapper(componentModel = "spring")
public interface ObjectsTypeMapper {

    ObjectsType mapToNewObjectType(NewObjectsTypeDto objectsTypesDto);

    ObjectsType mapToUpdateObjectType(UpdateObjectsTypeDto objectsTypesDto);

    ObjectsTypeDto mapToObjectTypeDto(ObjectsType objectsTypes);

    ObjectsType mapToObjectType(ObjectsTypeDto objectsTypesDto);
}