package ru.nabokovsg.temlservice.dto.passportData;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Шаблон паспортных данных")
public class SurveyObjectPassportDataTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Порядковый номер подраздела")
    private Double sequentialNumber;
    @Schema(description = "Наименование данных")
    private String dataName;
    @Schema(description = "Применить в отчете")
    private Boolean applyReport;
    @Schema(description = "Применить в протоколе")
    private Boolean applyProtocol;
}
