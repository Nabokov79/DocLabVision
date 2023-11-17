package ru.nabokovsg.dataservice.dto.application;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.address.AddressDto;
import ru.nabokovsg.dataservice.dto.surveyObject.ShortSurveyObjectDto;
import ru.nabokovsg.dataservice.dto.reportingDocument.ReportingDocumentDto;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class ShortApplicationDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Дата проведения обследования/контроля")
    private LocalDate date;
    @Schema(description = "Адрес места проведения обследования/контроля")
    private AddressDto address;
    @Schema(description = "Объект обследования/контроля")
    private ShortSurveyObjectDto surveyObject;
    @Schema(description = "Вид выполненной работы")
    private String workPerformed;
    @Schema(description = "Отчетный документ")
    private ReportingDocumentDto reportingDocument;
    @Schema(description = "Основание для проведения работы по обследованию")
    private String taskSource;
}