package ru.nabokovsg.temlservice.dto.appendices;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные приложения к отчету, протоколу, заключению")
public class AppendicesTemplateDto {

    @Schema(description = "Индентификатор")
    private long id;
    @Schema(description = "Порядковый номер приложения")
    private Integer sequentialAppendicesNumber;
    @Schema(description = "Название приложения")
    private String appendicesName;
}