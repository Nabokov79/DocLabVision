package ru.nabokovsg.dataservice.dto.reportingDocumentData;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@AllArgsConstructor
public class DocumentDataDto {

    @Schema(description = "Индентификатор данных документа")
    @NotNull(message = "id application should not be null")
    @Positive(message = "id application must be positive")
    private Long id;
    @Schema(description = "Путь к файлу")
    private String path;
    @Schema(description = "Индентификатор сотрудника")
    private Long employeeId;
}
