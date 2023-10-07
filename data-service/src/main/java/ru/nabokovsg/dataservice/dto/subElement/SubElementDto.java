package ru.nabokovsg.dataservice.dto.subElement;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.place.PlaceDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные подэлемента элемента объекта")
public class SubElementDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Название подэлемента")
    private String subElementName;
    @Schema(description = "Участок подэлемента")
    private List<PlaceDto> places;
}