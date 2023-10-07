package ru.nabokovsg.dataservice.dto.subElement;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Краткие данные подэлемента элемента объекта")
public class ShortSubElementDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Название подэлемента")
    private String subElementName;
}
