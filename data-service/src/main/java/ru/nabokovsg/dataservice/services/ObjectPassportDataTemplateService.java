package ru.nabokovsg.dataservice.services;

import ru.nabokovsg.dataservice.dto.objectPassportDataTemplate.NewObjectPassportDataTemplateDto;
import ru.nabokovsg.dataservice.dto.objectPassportDataTemplate.ObjectPassportDataTemplateDto;
import ru.nabokovsg.dataservice.dto.objectPassportDataTemplate.UpdateObjectPassportDataTemplateDto;
import ru.nabokovsg.dataservice.dto.objectsType.ObjectsTypePassportDataTemplateDto;

import java.util.List;

public interface ObjectPassportDataTemplateService {

    List<ObjectsTypePassportDataTemplateDto> save(List<Long> objectsTypeId
                                            , List<NewObjectPassportDataTemplateDto> templatesDto);

    List<ObjectPassportDataTemplateDto> update(List<UpdateObjectPassportDataTemplateDto> templatesDto);
}