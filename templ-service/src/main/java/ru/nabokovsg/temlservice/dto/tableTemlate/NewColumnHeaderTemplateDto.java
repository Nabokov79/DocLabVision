package ru.nabokovsg.temlservice.dto.tableTemlate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные новой колонки таблицы документа")
public class NewColumnHeaderTemplateDto {

    @Schema(description = "Порядковый номер колонки")
    @NotNull(message = "sequential cell number should not be null")
    @Positive(message = "sequential cell number must be positive")
    private Integer sequentialCellNumber;
    @Schema(description = "Название колонки")
    @NotBlank(message = "cell name should not be blank")
    private String cellName;
    @Schema(description = "Тип данных колонки")
    private String columnDataType;
}