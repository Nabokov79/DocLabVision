package ru.nabokovsg.dataservice.dto.subElement;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные подэлемента элемента объекта")
public class SubElementDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Порядковый номер подэлемента")
    private Integer ordinalNumberSubElement;
    @Schema(description = "Название подэлемента")
    private String subElementName;
}