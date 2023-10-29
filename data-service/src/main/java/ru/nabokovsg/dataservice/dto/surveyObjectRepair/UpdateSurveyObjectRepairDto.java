package ru.nabokovsg.dataservice.dto.surveyObjectRepair;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения информации о ремонте")
public class UpdateSurveyObjectRepairDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "repair id should not be blank")
    @Positive(message = "repair id must be positive")
    private Long id;
    @Schema(description = "Дата ремонта")
    @NotNull(message = "date repair should not be blank")
    private String date;
    @Schema(description = "Описание ремонта")
    @NotBlank(message = "description repair should not be blank")
    private String description;
    @Schema(description = "Организация, выполнившея ремонт")
    @NotBlank(message = "organization should not be blank")
    private String organization;
}