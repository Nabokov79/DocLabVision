package ru.nabokovsg.dataservice.services;

import ru.nabokovsg.dataservice.dto.documentation.DocumentationDto;
import ru.nabokovsg.dataservice.dto.documentation.NewDocumentationDto;
import ru.nabokovsg.dataservice.dto.documentation.UpdateDocumentationDto;
import ru.nabokovsg.dataservice.dto.objectsTypeData.ObjectsTypeDocumentationDataDto;
import java.util.List;


public interface DocumentationService {

    List<ObjectsTypeDocumentationDataDto> save(List<Long> objectsTypeId, List<NewDocumentationDto> documentationsDto);

    List<DocumentationDto> update(List<UpdateDocumentationDto> documentationsDto);
}