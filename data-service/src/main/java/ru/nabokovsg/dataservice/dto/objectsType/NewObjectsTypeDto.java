package ru.nabokovsg.dataservice.dto.objectsType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.plotOfObject.NewPlotOfObjectDto;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные нового типа объекта")
public class NewObjectsTypeDto {

    @Schema(description = "Название объекта")
    @NotBlank(message = "name should not be blank")
    private String object;
    @Schema(description = "Положение объекта")
    private String orientation;
    @Schema(description = "Объем объекта")
    private Integer volume;
    @Schema(description = "Список участков объекта")
    private List<NewPlotOfObjectDto> plotOfObjects;
}