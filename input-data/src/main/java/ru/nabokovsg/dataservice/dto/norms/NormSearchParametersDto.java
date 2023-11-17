package ru.nabokovsg.dataservice.dto.norms;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Schema(description = "Параметры поиска нормы брака")
public class NormSearchParametersDto {

    @Schema(description = "Индентификатор элемента объекта")
    private Long elementId;
    @Schema(description = "Индентификатор подэлемента элемента объекта")
    private Long subElementId;
    @Schema(description = "Диаметр элемента объекта")
    private Integer diameter;
    @Schema(description = "Толщина элемента объекта")
    private Float thickness;
}