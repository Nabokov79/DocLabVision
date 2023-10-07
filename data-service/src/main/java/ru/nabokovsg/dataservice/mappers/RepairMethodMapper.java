package ru.nabokovsg.dataservice.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.repairMethod.NewRepairMethodDto;
import ru.nabokovsg.dataservice.dto.repairMethod.RepairMethodDto;
import ru.nabokovsg.dataservice.dto.repairMethod.UpdateRepairMethodDto;
import ru.nabokovsg.dataservice.models.RepairMethod;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RepairMethodMapper {

    List<RepairMethod> mapToNewRepairMethod(List<NewRepairMethodDto> methodsDto);

    List<RepairMethod> mapToUpdateRepairMethod(List<UpdateRepairMethodDto> methodsDto);

    List<RepairMethodDto> mapToNewRepairMethodDto(List<RepairMethod> methods);
}