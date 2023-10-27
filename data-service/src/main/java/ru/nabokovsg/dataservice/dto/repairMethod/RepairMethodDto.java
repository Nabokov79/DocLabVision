package ru.nabokovsg.dataservice.dto.repairMethod;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.objectsType.ObjectsTypeDto;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные способа ремонта")
public class RepairMethodDto {

    @Schema(description = "Индентификатор")
    private Long id;
    private ObjectsTypeDto objectsType;
    @Schema(description = "Название способа ремонта")
    private String methodName;
}