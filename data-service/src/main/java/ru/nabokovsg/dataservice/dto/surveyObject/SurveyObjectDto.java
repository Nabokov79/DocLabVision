package ru.nabokovsg.dataservice.dto.surveyObject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.building.BuildingDto;
import ru.nabokovsg.dataservice.dto.objectsSurveyElement.ObjectsSurveyElementDto;
import ru.nabokovsg.dataservice.dto.objectsType.ShortObjectsTypeDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные объекта")
public class SurveyObjectDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Тип объекта")
    private ShortObjectsTypeDto objectsType;
    @Schema(description = "Стационарный номер объекта")
    private Integer stationaryNumber;
    @Schema(description = "Данные элементов объекта обследования")
    private List<ObjectsSurveyElementDto> elements;
    @Schema(description = "Строение")
    private BuildingDto building;
}