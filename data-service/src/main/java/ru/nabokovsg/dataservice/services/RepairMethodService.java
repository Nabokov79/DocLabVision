package ru.nabokovsg.dataservice.services;

import ru.nabokovsg.dataservice.dto.objectsTypeData.ObjectsTypeRepairMethodDataDto;
import ru.nabokovsg.dataservice.dto.repairMethod.NewRepairMethodDto;
import ru.nabokovsg.dataservice.dto.repairMethod.RepairMethodDto;
import ru.nabokovsg.dataservice.dto.repairMethod.UpdateRepairMethodDto;
import java.util.List;


public interface RepairMethodService {

    List<ObjectsTypeRepairMethodDataDto> save(List<Long> objectsTypeId, List<NewRepairMethodDto> methodsDto);

    List<RepairMethodDto> update(List<UpdateRepairMethodDto> methodsDto);
}