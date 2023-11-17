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
@Schema(description = "Данные новой таблицы подраздела")
public class NewSubsectionTableTemplateDto {

    @Schema(description = "Индентификатор раздела")
    @NotNull(message = "section id should not be null")
    @Positive(message = "section id must be positive")
    private Long sectionId;
    @Schema(description = "Индентификатор подраздела")
    @NotNull(message = "subsection id should not be null")
    @Positive(message = "subsection id must be positive")
    private Long subsectionId;
    @Schema(description = "Тип данных таблицы")
    @NotBlank(message = "table data type should not be blank")
    private String tableDataType;
    @Schema(description = "Шаблоны колонок таблицы")
    @NotNull(message = "column headers should not be null")
    @NotEmpty(message = "column headers id should not be empty")
    private List<@Valid NewColumnHeaderTemplateDto> columnHeaders;
}