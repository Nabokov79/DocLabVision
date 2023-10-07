package ru.nabokovsg.dataservice.dto.objectPassportDataTemplate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения шаблона паспортных данных объекта")
public class ObjectPassportDataTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Порядковый номер подраздела")
    private String sequentialSubsectionNumber;
    @Schema(description = "Номер раздела")
    private Integer sectionNumber;
    @Schema(description = "Номер подраздела")
    private Integer subsectionNumber;
    @Schema(description = "Индентификатор типа объекта")
    private String dataName;
}