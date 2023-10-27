package ru.nabokovsg.temlservice.dto.report;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.temlservice.dto.pageTitle.NewPageTitleTemplateDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные нового шаблона отчета")
public class NewReportTemplateDto {

    @Schema(description = "Данные нового титульного листа")
    @NotNull(message = "page title template should not be null")
    private NewPageTitleTemplateDto pageTitleTemplate;
    @Schema(description = "Индентификатор отчетного документа")
    @NotNull(message = "reporting document id should not be null")
    @Positive(message = "reporting document id can only be positive")
    private Long reportingDocumentId;
    @Schema(description = "Индентификатор типа объекта")
    @NotNull(message = "object type id should not be null")
    @Positive(message = "object type id can only be positive")
    private Long objectsTypeId;
}
