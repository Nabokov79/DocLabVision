package ru.nabokovsg.dataservice.dto.objectsTypeData;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.objectsType.ObjectsTypeDto;
import ru.nabokovsg.dataservice.dto.repairMethod.RepairMethodDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class ObjectsTypeRepairMethodDataDto {

    @Schema(description = "Тип объекта")
    private ObjectsTypeDto objectsType;
    @Schema(description = "Название видов ремонта объекта")
    private List<RepairMethodDto> repairMethods;
}