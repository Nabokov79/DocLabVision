package ru.nabokovsg.temlservice.dto.subsection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные нового подраздела раздела отчета, подаздела протокола, подраздела заключения")
public class NewSectionSubsectionTemplateDto {

    @Schema(description = "Индентификатор типа объекта")
    @NotNull(message = "object type id should not be null")
    @Positive(message = "object type id must be positive")
    private Long objectsTypeId;
    @Schema(description = "Индентификатор типа отчетного документа")
    @NotNull(message = "reporting document id should not be null")
    @Positive(message = "reporting document id must be positive")
    private Long reportingDocumentId;
    @Schema(description = "Индентификатор раздела отчета")
    private Long sectionId;
    @Schema(description = "Подразделы принадлежат протоколу, заключению")
    @NotNull(message = "protocol template should not be null")
    private boolean protocolTemplate;
    @Schema(description = "Подразделы раздела отчета, протокола, заключения")
    @NotNull(message = "section templates should not be null")
    @NotEmpty(message = "section templates should not be empty")
    private List<NewSubsectionTemplateDto> subsectionTemplates;
}