package ru.nabokovsg.temlservice.dto.subsection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.temlservice.models.enums.DataType;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные шаблона подраздела протокола отчета")
public class ProtocolSubsectionTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Тип данных подраздела")
    private DataType dataType;
    @Schema(description = "Порядковый номер подраздела")
    private double sequentialSubsectionNumber;
    @Schema(description = "Название подраздела")
    private String subsectionName;
    @Schema(description = "Текст подраздела")
    private String subsectionText;
    @Schema(description = "Показать номер подраздела в документе")
    private boolean subsectionNumber;
}