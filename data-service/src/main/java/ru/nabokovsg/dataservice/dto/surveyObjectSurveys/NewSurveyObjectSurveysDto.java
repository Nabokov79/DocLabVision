package ru.nabokovsg.dataservice.dto.surveyObjectSurveys;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Данные нового обследования")
public class NewSurveyObjectSurveysDto {

    @Schema(description = "Индентификатор объекта обследования")
    @NotNull(message = "id object data should not be blank")
    @Positive(message = "id object data can only be positive")
    private Long objectId;
    @Schema(description = "Дата обследования")
    @NotNull(message = "date repair should not be blank")
    private String date;
    @Schema(description = "Обследование")
    @NotBlank(message = "survey should not be blank")
    private String surveyDescription;
    @Schema(description = "Номер отчета обследования")
    @NotBlank(message = "number should not be blank")
    private String surveyNumber;
    @Schema(description = "Организация, выполнившея ремонт")
    @NotBlank(message = "organization should not be blank")
    private String organization;
    @Schema(description = "Дата следующего обследования")
    private String dateNextSurvey;
}