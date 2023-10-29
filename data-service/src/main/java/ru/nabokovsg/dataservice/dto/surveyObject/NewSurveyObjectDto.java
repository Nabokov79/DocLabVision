package ru.nabokovsg.dataservice.dto.surveyObject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные нового объекта")
public class NewSurveyObjectDto {

    @Schema(description = "Индентификатор типа объекта")
    @NotNull(message = "id organization's address must not be null")
    @Positive(message = "id organization's address must be negative")
    private Long objectsTypeId;
    @Schema(description = "Стационарный номер объекта")
    private Integer stationaryNumber;
    @Schema(description = "Индентификатор строения")
    @NotNull(message = "id organization's address must not be null")
    @Positive(message = "id organization's address must be negative")
    private Long buildingId;
}