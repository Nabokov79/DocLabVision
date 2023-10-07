package ru.nabokovsg.dataservice.dto.objectsTypeData;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.defect.DefectDto;
import ru.nabokovsg.dataservice.dto.objectsType.ObjectsTypeDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class ObjectsTypeDefectDataDto {

    @Schema(description = "Тип объекта")
    private ObjectsTypeDto objectsType;
    @Schema(description = "Дефекты типа объекта")
    private List<DefectDto> defects;
}