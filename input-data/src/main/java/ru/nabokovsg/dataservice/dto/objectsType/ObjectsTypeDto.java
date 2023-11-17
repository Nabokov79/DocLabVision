package ru.nabokovsg.dataservice.dto.objectsType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.defect.DefectDto;
import ru.nabokovsg.dataservice.dto.documentation.DocumentationDto;
import ru.nabokovsg.dataservice.dto.element.ElementDto;
import ru.nabokovsg.dataservice.dto.norms.NormsDto;
import ru.nabokovsg.dataservice.dto.objectPassportDataTemplate.ObjectPassportDataTemplateDto;
import ru.nabokovsg.dataservice.dto.repairMethod.RepairMethodDto;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные типа объекта")
public class ObjectsTypeDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Название объекта")
    private String objectName;
    @Schema(description = "Объем объекта")
    private Integer volume;
    @Schema(description = "Модель объекта")
    private String model;
    @Schema(description = "Положение объекта")
    private String orientation;
    @Schema(description = "Элементы объекта")
    private List<ElementDto> elements;
    @Schema(description = "Нормативно-техничсекая документация на объект")
    private List<DocumentationDto> documentations;
    @Schema(description = "Дефекты объекта")
    private List<DefectDto> defects;
    @Schema(description = "Нормы оценки дефектов объекта")
    private List<NormsDto> norms;
    @Schema(description = "Шаблоны названий поспортных данных объекта")
    private List<ObjectPassportDataTemplateDto> passportData;
    @Schema(description = "Способы ремонта объекта")
    private List<RepairMethodDto> repairMethods;
}