package ru.nabokovsg.dataservice.dto.objectsType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.plotOfObject.UpdatePlotOfObjectDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения информации о типе объекта")
public class UpdateObjectsTypeDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id should not be blank")
    @Positive(message = "id must be positive")
    private Long id;
    @Schema(description = "Название объекта")
    @NotBlank(message = "name should not be blank")
    private String object;
    @Schema(description = "Положение объекта")
    private String orientation;
    @Schema(description = "Объем объекта")
    private Integer volume;
    @Schema(description = "Список участков объекта")
    private List<UpdatePlotOfObjectDto> plotOfObjects;
}