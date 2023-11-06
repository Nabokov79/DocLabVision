package ru.nabokovsg.temlservice.dto.tableTemlate;

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
@Schema(description = "Данные новой таблицы документа")
public class NewTableTemplateDto {

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
    @Schema(description = "Порядковый номер подраздела")
    private double sequentialSubsectionNumber;
    @Schema(description = "Тип данных таблицы")
    private String tableDataType;
    @Schema(description = "Порядковый номер таблицы")
    private Integer sequentialTableNumber;
    @Schema(description = "Название таблицы")
    private String tableName;
    @Schema(description = "Шаблоны колонок таблицы")
    @NotNull(message = "column headers should not be null")
    @NotEmpty(message = "column headers id should not be empty")
    private List<NewColumnHeaderTemplateDto> columnHeaders;
}