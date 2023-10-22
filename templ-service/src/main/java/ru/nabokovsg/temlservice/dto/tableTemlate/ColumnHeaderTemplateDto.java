package ru.nabokovsg.temlservice.dto.tableTemlate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.temlservice.enums.ColumnDataType;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные колонки таблицы документа")
public class ColumnHeaderTemplateDto {

    @Schema(description = "Индентификатор")
    private long id;
    @Schema(description = "Порядковый номер колонки")
    private Integer sequentialCellNumber;
    @Schema(description = "Название колонки")
    private String cellName;
    @Schema(description = "Тип данных колонки")
    private ColumnDataType columnDataType;
}