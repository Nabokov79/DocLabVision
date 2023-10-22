package ru.nabokovsg.temlservice.dto.tableTemlate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные новой таблицы документа")
public class NewTableTemplateDto {

    @Schema(description = "Тип данных таблицы")
    private String tableDataType;
    @Schema(description = "Порядковый номер таблицы")
    private Integer sequentialTableNumber;
    @Schema(description = "Название таблицы")
    private String tableName;
    @Schema(description = "Шаблоны колонок таблицы")
    private List<NewColumnHeaderTemplateDto> columnHeaders;
}