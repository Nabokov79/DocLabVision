package ru.nabokovsg.dataservice.dto.plotOfObject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения данных участка типа объекта обследования")
public class UpdatePlotOfObjectDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "plot of object id should not be blank")
    @Positive(message = "plot of object id can only be positive")
    private Long id;
    @Schema(description = "Название участка")
    @NotBlank(message = "plot name should not be blank")
    private String plotName;
}