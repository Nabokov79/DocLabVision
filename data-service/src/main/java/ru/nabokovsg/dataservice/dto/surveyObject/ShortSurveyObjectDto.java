package ru.nabokovsg.dataservice.dto.surveyObject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.objectsType.ObjectsTypeDto;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Краткие данные объекта")
public class ShortSurveyObjectDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Тип объекта")
    private ObjectsTypeDto objectsType;
    @Schema(description = "Объем объекта")
    private Integer number;
}