package ru.nabokovsg.dataservice.dto.norms;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.element.ShortElementDto;
import ru.nabokovsg.dataservice.dto.subElement.ShortSubElementDto;

@Setter
@Getter
@AllArgsConstructor
public class NormsDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Элемент объекта")
    private ShortElementDto element;
    @Schema(description = "Подэлемент элемента объекта")
    private ShortSubElementDto subElement;
    @Schema(description = "Предельно-минимальное допустимое значение в процентах от номинальной толщины элемента")
    private Float minInPercent;
    @Schema(description = "Предельно-минимальное допустимое значение в миллиметрах")
    private Float min;
    @Schema(description = "Значение допуска отклонения от номинального значения")
    private Float sizeTolerance;
    @Schema(description = "допустимое отклонение от нормы")
    private Float measurementError;
}