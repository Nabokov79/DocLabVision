package ru.nabokovsg.temlservice.dto.reportProtocol;

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
@Schema(description = "Данные нового шаблона протокола отчета")
public class NewReportProtocolTemplateDto {

    @Schema(description = "Индентификатор раздела")
    @NotNull(message = "sequentialProtocolNumber should not be null")
    @Positive(message = "sequentialProtocolNumber can only be positive")
    private Long sectionId;
    @Schema(description = "Порядковый номер протокола")
    @NotNull(message = "sequentialProtocolNumber should not be null")
    @Positive(message = "sequentialProtocolNumber can only be positive")
    private Integer sequentialProtocolNumber;
    @Schema(description = "Индентификатор отчетного документа")
    @NotNull(message = "reporting document id should not be null")
    @Positive(message = "reporting document id can only be positive")
    private Long reportingDocumentId;
    @Schema(description = "Текст в протоколе")
    private String protocolText;
}