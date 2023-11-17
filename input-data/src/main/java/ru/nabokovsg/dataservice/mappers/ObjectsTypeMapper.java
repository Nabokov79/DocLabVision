package ru.nabokovsg.dataservice.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.objectsType.*;
import ru.nabokovsg.dataservice.models.ObjectsType;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ObjectsTypeMapper {

    ObjectsType mapToNewObjectType(NewObjectsTypeDto objectsTypesDto);

    ObjectsType mapToUpdateObjectType(UpdateObjectsTypeDto objectsTypesDto);

    ObjectsTypeDto mapToObjectTypeDto(ObjectsType objectsTypes);

    List<ShortObjectsTypeDto> mapToShortObjectsTypeDto(List<ObjectsType> objectsTypes);

    ObjectsTypeDocumentationDto mapToObjectsTypeDocumentationDto(ObjectsType objectsType);

    List<ObjectsTypeElementsDto> mapToObjectsTypeElementsDto(List<ObjectsType> objectsType);

    ObjectsTypeDefectDto mapToObjectsTypeDefectDto(ObjectsType objectsType);

    ObjectsTypeRepairMethodDto mapToObjectsTypeRepairMethodDto(ObjectsType objectsType);

    ObjectsTypePassportDataTemplateDto mapToObjectsTypePassportDataTemplateDto(ObjectsType objectsType);

    ObjectsTypeNormDto mapToObjectsTypeNormDto(ObjectsType objectsType);

    List<NewObjectsTypeDto> mapToNewObjectTypeDto(List<ObjectsType> objectsTypes);

    ObjectsTypeElementsDto mapToObjectTypeElementsDto(ObjectsType objectType);
}