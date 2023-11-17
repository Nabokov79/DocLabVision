package ru.nabokovsg.dataservice.dto.surveyObjectRepair;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Данные нового ремонта")
public class NewSurveyObjectRepairDto {

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