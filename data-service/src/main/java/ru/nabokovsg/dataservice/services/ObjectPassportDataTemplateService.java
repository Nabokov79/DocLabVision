package ru.nabokovsg.dataservice.services;

import ru.nabokovsg.dataservice.dto.objectPassportDataTemplate.NewObjectPassportDataTemplateDto;
import ru.nabokovsg.dataservice.dto.objectPassportDataTemplate.ObjectPassportDataTemplateDto;
import ru.nabokovsg.dataservice.dto.objectPassportDataTemplate.UpdateObjectPassportDataTemplateDto;
import java.util.List;

public interface ObjectPassportDataTemplateService {

    List<ObjectPassportDataTemplateDto> save(List<Long> objectsTypeId
                                            , List<NewObjectPassportDataTemplateDto> templatesDto);

    List<ObjectPassportDataTemplateDto> update(List<UpdateObjectPassportDataTemplateDto> templatesDto);
}