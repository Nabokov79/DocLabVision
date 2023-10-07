package ru.nabokovsg.dataservice.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.objectsTypeData.*;
import ru.nabokovsg.dataservice.models.ObjectsTypeData;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ObjectsTypeDataMapper {

    List<ObjectsTypeDocumentationDataDto> mapToObjectsTypeDocumentationDataDto(List<ObjectsTypeData> objectsTypeData);

    List<ObjectsTypeTemplateDataDto> mapToObjectsTypeTemplateDataDto(List<ObjectsTypeData> objectsTypeData);

    List<ObjectsTypeRepairMethodDataDto> mapToObjectsTypeRepairMethodDataDto(List<ObjectsTypeData> objectsTypeData);

    List<ObjectsTypeDefectDataDto> mapToObjectsTypeDefectDataDto(List<ObjectsTypeData> objectsTypeData);

    List<ObjectsTypeElementDataDto> mapToObjectsTypeElementDataDto(List<ObjectsTypeData> objectsTypeData);

    List<ObjectsTypeNormsDataDto> mapToObjectsTypeNormsDataDto(List<ObjectsTypeData> objectsTypeData);

    ObjectsTypeDataDto mapToObjectsTypeDataDto(ObjectsTypeData objectsTypeData);
}