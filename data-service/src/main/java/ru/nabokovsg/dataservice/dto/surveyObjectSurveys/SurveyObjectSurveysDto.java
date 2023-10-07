package ru.nabokovsg.dataservice.dto.surveyObjectSurveys;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные обследования")
public class SurveyObjectSurveysDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Дата обследования")
    private String date;
    @Schema(description = "Обследование")
    private String surveyDescription;
    @Schema(description = "Номер отчета обследования")
    private String surveyNumber;
    @Schema(description = "Организация, выполнившея ремонт")
    @NotBlank(message = "organization should not be blank")
    private String organization;
    @Schema(description = "Дата следующего обследования")
    private String dateNextSurvey;
}