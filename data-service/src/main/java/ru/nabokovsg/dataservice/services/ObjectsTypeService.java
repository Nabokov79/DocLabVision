package ru.nabokovsg.dataservice.services;

import ru.nabokovsg.dataservice.dto.objectsType.*;
import ru.nabokovsg.dataservice.models.*;

import java.util.List;

public interface ObjectsTypeService {

    List<ShortObjectsTypeDto> save(List<NewObjectsTypeDto> objectsTypeDto);

    List<ShortObjectsTypeDto> update(List<UpdateObjectsTypeDto> objectsTypeDto);

    ObjectsTypeDto get(Long id);

    ObjectsType getById(Long id);

    List<ObjectsTypeDocumentationDto> addDocumentations(List<Long> ids, List<Documentation> documentations);

    List<ObjectsTypeElementsDto> addElements(List<Long> ids, List<Element> elements);

    List<ObjectsTypeDefectDto> addDefects(List<Long> ids, List<Defect> defects);

    List<ObjectsTypeRepairMethodDto> addRepairMethods(List<Long> ids, List<RepairMethod> methods);

    List<ObjectsTypePassportDataTemplateDto> addObjectPassportDataTemplates(List<Long> ids
                                                                          , List<ObjectPassportDataTemplate> templates);

    List<ObjectsTypeNormDto> addNorms(List<Long> ids, List<Norm> norms);

    List<ObjectsType> getAll(List<Long> ids);

    void delete(Long id);
}