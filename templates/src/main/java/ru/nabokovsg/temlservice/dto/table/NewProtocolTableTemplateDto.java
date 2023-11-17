package ru.nabokovsg.temlservice.dto.table;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные новой таблицы протокола")
public class NewProtocolTableTemplateDto {

    @Schema(description = "Индентификатор протокола")
    @NotNull(message = "protocol id should not be null")
    @Positive(message = "protocol id must be positive")
    private Long protocolId;
    @Schema(description = "Тип данных таблицы")
    @NotBlank(message = "tableDataType should not be blank")
    private String tableDataType;
    @Schema(description = "Порядковый номер таблицы")
    @NotNull(message = "sequentialTableNumber should not be null")
    @Positive(message = "sequentialTableNumber must be positive")
    private Integer sequentialTableNumber;
    @Schema(description = "Название таблицы")
    @NotBlank(message = "Protocol table name should not be blank")
    private String tableName;
    @Schema(description = "Шаблоны колонок таблицы")
    @NotNull(message = "column headers should not be null")
    @NotEmpty(message = "column headers id should not be empty")
    private List<@Valid NewColumnHeaderTemplateDto> columnHeaders;
}