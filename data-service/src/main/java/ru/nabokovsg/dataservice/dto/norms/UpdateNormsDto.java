package ru.nabokovsg.dataservice.dto.norms;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения норм брака объекта обследования")
public class UpdateNormsDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id should not be blank")
    @Positive(message = "id can only be positive")
    private Long id;
    @Schema(description = "Индентификатор элемента объекта")
    @NotNull(message = "element id should not be blank")
    @Positive(message = "element id can only be positive")
    private Long elementId;
    @Schema(description = "Индентификатор подэлемента элемента объекта")
    private Long subElementId;
    @Schema(description = "Диаметр элемента объекта")
    private Integer diameter;
    @Schema(description = "Толщина элемента объекта")
    private Float thickness;
    @Schema(description = "Предельно-минимальное допустимое значение в процентах от номинальной толщины элемента")
    private Float minInPercent;
    @Schema(description = "Предельно-минимальное допустимое значение в миллиметрах")
    @Positive(message = "min can only be positive")
    private Float min;
    @Schema(description = "допустимое отклонение от нормы")
    @NotNull(message = "measurement error should not be blank")
    @Positive(message = "measurement error can only be positive")
    private Float measurementError;
}