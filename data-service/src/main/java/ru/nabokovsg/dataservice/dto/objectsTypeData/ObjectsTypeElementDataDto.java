package ru.nabokovsg.dataservice.dto.objectsTypeData;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.element.ElementDto;
import ru.nabokovsg.dataservice.dto.objectsType.ObjectsTypeDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class ObjectsTypeElementDataDto {

    @Schema(description = "Тип объекта")
    private ObjectsTypeDto objectsType;
    @Schema(description = "Элементы типа объекта")
    private List<ElementDto> elements;
}