package ru.nabokovsg.temlservice.dto.tableTemlate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные таблицы раздела или подраздела документа")
public class TableTemplateDto {

    @Schema(description = "Индентификатор")
    private long id;
    @Schema(description = "Порядковый номер таблицы")
    private Integer sequentialTableNumber;
    @Schema(description = "Название таблицы")
    private String tableName;
    @Schema(description = "Шаблон колонки таблицы")
    private List<ColumnHeaderTemplateDto> columnsHeaders;
}