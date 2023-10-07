package ru.nabokovsg.dataservice.dto.objectsSurveyElement;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Новые данные элементов объекта обследования")
public class NewObjectsSurveyElementDto {

    @Schema(description = "Индентификатор элемента объекта обследования")
    @NotNull(message = "element id must not be null")
    @Positive(message = "element id must be negative")
    private Long elementId;
    @Schema(description = "Индентификатор подэлемента элемента объекта обследования")
    private Long subElementId;
    @Schema(description = "Толщина элемента объекта обследования")
    private Float thickness;
    @Schema(description = "Минимальный диаметр элемента объекта обследования")
    private Integer pipeDiameterMin;
    @Schema(description = "Толщина минимального диаметра элемента объекта обследования")
    private Float pipeWallThicknessMin;
    @Schema(description = "Максимальный диаметр элемента объекта обследования")
    private Integer pipeDiameterMax;
    @Schema(description = "Толщина максимального диаметра элемента объекта обследования")
    private Float pipeWallThicknessMax;
}