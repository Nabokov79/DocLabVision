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
@Schema(description = "Шаблон паспортных данных")
public class NewSurveyObjectPassportDataTemplateDto {

    @Schema(description = "Индентификатор типа объекта")
    @NotNull(message = "object type id should not be null")
    @Positive(message = "object type id can only be positive")
    private Long objectsTypeId;
    @Schema(description = "Порядковый номер")
    @NotNull(message = "data sequence number must not be null")
    @Positive(message = "data sequence number must be negative")
    private Double sequentialNumber;
    @Schema(description = "Наименование данных")
    @NotBlank(message = "data name must not be null")
    private String dataName;
    @Schema(description = "Применить данные в отчете - true - да, false- нет")
    @NotNull(message = "applyProtocol must not be null")
    private Boolean applyReport;
    @Schema(description = "Применить данные в протоколе - true - да, false- нет")
    @NotNull(message = "applyProtocol must not be null")
    private Boolean applyProtocol;
}
