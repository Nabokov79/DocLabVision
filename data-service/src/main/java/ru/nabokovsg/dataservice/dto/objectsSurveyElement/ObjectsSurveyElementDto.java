package ru.nabokovsg.dataservice.dto.objectsSurveyElement;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.element.ShortElementDto;
import ru.nabokovsg.dataservice.dto.subElement.SubElementDto;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные об элементах объекта обследования")
public class ObjectsSurveyElementDto {

    @Schema(description = "Краткие сведения об элементе объекта обследования")
    private ShortElementDto element;
    @Schema(description = "Краткие сведения о подэлементе элемента объекта обследования")
    private SubElementDto subElement;
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