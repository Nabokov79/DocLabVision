package ru.nabokovsg.temlservice.services.converters;

import ru.nabokovsg.temlservice.dto.client.ObjectsTypeDto;
import ru.nabokovsg.temlservice.enums.SubsectionDataType;
import ru.nabokovsg.temlservice.models.SubsectionDataTemplate;

import java.util.List;

public interface ConvertDocumentationDataService {

    List<SubsectionDataTemplate> convert(SubsectionDataType subsectionDataType, ObjectsTypeDto objectsType);
}