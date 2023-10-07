package ru.nabokovsg.dataservice.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.objectsType.NewObjectsTypeDto;
import ru.nabokovsg.dataservice.dto.objectsType.ObjectsTypeDto;
import ru.nabokovsg.dataservice.dto.objectsType.UpdateObjectsTypeDto;
import ru.nabokovsg.dataservice.models.ObjectsType;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ObjectsTypeMapper {

    ObjectsType mapToNewObjectsType(NewObjectsTypeDto objectsTypesDto);

    ObjectsType mapToUpdateObjectsType(UpdateObjectsTypeDto objectsTypesDto);

    List<ObjectsTypeDto> mapToObjectsTypeDto(List<ObjectsType> objectsTypes);
}