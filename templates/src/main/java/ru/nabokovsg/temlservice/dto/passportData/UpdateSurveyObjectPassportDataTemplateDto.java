package ru.nabokovsg.temlservice.dto.passportData;

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
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Новый шаблон паспортных данных")
public class UpdateSurveyObjectPassportDataTemplateDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id should not be null")
    @Positive(message = "id can only be positive")
    private Long id;
    @Schema(description = "Порядковый номер")
    @NotNull(message = "data sequence number must not be null")
    @Positive(message = "data sequence number must be negative")
    private Double sequentialNumber;
    @Schema(description = "Наименование данных")
    @NotBlank(message = "data name must not be null")
    private String dataName;
    @Schema(description = "Применить данные в протоколе - true - да, false- нет")
    @NotNull(message = "applyProtocol must not be null")
    private Boolean applyProtocol;
}
