package ru.nabokovsg.dataservice.dto.plotOfObject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные участка типа объекта обследования")
public class PlotOfObjectDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Название участка")
    private String plotName;
}