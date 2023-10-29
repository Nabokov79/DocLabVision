package ru.nabokovsg.dataservice.services;

import org.springframework.validation.annotation.Validated;
import ru.nabokovsg.dataservice.dto.norms.NewNormsDto;
import ru.nabokovsg.dataservice.dto.norms.NormsDto;
import ru.nabokovsg.dataservice.dto.norms.UpdateNormsDto;
import ru.nabokovsg.dataservice.dto.objectsType.ObjectsTypeNormDto;

import java.util.List;

@Validated

public interface NormsService {

    List<ObjectsTypeNormDto> save(List<Long> objectsTypeId, List<NewNormsDto> normsDto);

    List<NormsDto> update(List<UpdateNormsDto> normsDto);
}