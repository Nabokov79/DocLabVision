package ru.nabokovsg.temlservice.dto.templates;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.temlservice.dto.pageTitle.NewPageTitleDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные нового шаблона протокола")
public class NewTemplateDto {

    @Schema(description = "Тип документа")
    @NotBlank(message = "document_type should not be blank")
    private String documentType;
    @Schema(description = "Индентификатор")
    @NotNull(message = "object type id should not be blank")
    @Positive(message = "object type id must be positive")
    private Long objectsTypeId;
    @Schema(description = "Индентификатор")
    @NotNull(message = "reporting document id should not be blank")
    @Positive(message = "reporting document id must be positive")
    private Long reportingDocumentId;
    @Schema(description = "Индентификатор")
    @NotNull(message = "id should not be blank")
    private NewPageTitleDto pageTitleDto;
}