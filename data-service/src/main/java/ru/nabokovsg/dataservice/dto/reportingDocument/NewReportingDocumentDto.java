package ru.nabokovsg.dataservice.dto.reportingDocument;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Данные типа документа")
public class NewReportingDocumentDto {

    @Schema(description = "Название документа")
    @NotBlank(message = "reporting document should not be blank")
    private String document;
    @Schema(description = "заголовок документа")
    @NotBlank(message = "reporting document title should not be blank")
    private String documentTitle;
    @Schema(description = "Тип документа")
    @NotBlank(message = "reporting document type should not be blank")
    private String documentType;
    @Schema(description = "Тип протокола")
    private String protocolType;
}