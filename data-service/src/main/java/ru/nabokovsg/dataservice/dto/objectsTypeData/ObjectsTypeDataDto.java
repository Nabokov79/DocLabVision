package ru.nabokovsg.dataservice.dto.objectsTypeData;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.defect.DefectDto;
import ru.nabokovsg.dataservice.dto.documentation.DocumentationDto;
import ru.nabokovsg.dataservice.dto.element.ElementDto;
import ru.nabokovsg.dataservice.dto.norms.NormsDto;
import ru.nabokovsg.dataservice.dto.objectPassportDataTemplate.ObjectPassportDataTemplateDto;
import ru.nabokovsg.dataservice.dto.objectsType.ObjectsTypeDto;
import ru.nabokovsg.dataservice.dto.repairMethod.RepairMethodDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные типа объекта")
public class ObjectsTypeDataDto {

    @Schema(description = "Тип объекта")
    private ObjectsTypeDto objectsType;
    @Schema(description = "Элементы типа объекта")
    private List<ElementDto> elements;
    @Schema(description = "Дефекты типа объекта")
    private List<DefectDto> defects;
    @Schema(description = "Дефекты типа объекта")
    private List<NormsDto> norms;
    @Schema(description = "Нормативная документация к типу объекта")
    private List<DocumentationDto> documentations;
    @Schema(description = "Шаблон данных паспорта объекта")
    private List<ObjectPassportDataTemplateDto> templates;
    @Schema(description = "Шаблон данных паспорта объекта")
    private List<RepairMethodDto> repairMethods;
}