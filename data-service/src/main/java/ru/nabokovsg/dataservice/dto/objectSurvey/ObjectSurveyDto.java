package ru.nabokovsg.dataservice.dto.objectSurvey;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.building.BuildingDto;
import ru.nabokovsg.dataservice.dto.objectsSurveyElement.ObjectsSurveyElementDto;
import ru.nabokovsg.dataservice.dto.objectsType.ObjectsTypeDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные объекта")
public class ObjectSurveyDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Тип объекта")
    private ObjectsTypeDto objectsType;
    @Schema(description = "Модель объекта")
    private String model;
    @Schema(description = "Стационарный номер объекта")
    private Integer stationaryNumber;
    @Schema(description = "Данные элементов объекта обследования")
    private List<ObjectsSurveyElementDto> elements;
    @Schema(description = "Строение")
    private BuildingDto building;
}