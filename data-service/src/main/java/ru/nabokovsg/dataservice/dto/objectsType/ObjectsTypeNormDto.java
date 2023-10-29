package ru.nabokovsg.dataservice.dto.objectsType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.norms.NormsDto;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Schema(description = "Данные типа объекта и норм его браковки")
public class ObjectsTypeNormDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Название объекта")
    private String objectName;
    @Schema(description = "Модель объекта")
    private String model;
    @Schema(description = "Объем объекта")
    private Integer volume;
    @Schema(description = "Положение объекта")
    private String orientation;
    @Schema(description = "Нормы бюраковки объекта")
    private List<NormsDto> norms;
}