package ru.nabokovsg.dataservice.dto.documentRemark;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения информации о замечаниях")
public class UpdateDocumentRemarkDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id application should not be blank")
    @Positive(message = "id application must be positive")
    private Long id;
    @Schema(description = "Индентификатор даннных отчетного документа")
    @NotNull(message = "id reportingDocumentData should not be blank")
    @Positive(message = "id reportingDocumentData must be positive")
    private Long reportingDocumentDataId;
    @Schema(description = "Текст замечаний к оформлению документа")
    private String documentRemarkText;
    @Schema(description = "Текст замечаний к оформлению чертежа")
    private String drawingRemarkText;
    @Schema(description = "Индентификатор пользователя, оставившего замечание к чертежу")
    private Long employeeId;
    @Schema(description = "Отметка об исправлении замечания")
    private Boolean fixed;
}