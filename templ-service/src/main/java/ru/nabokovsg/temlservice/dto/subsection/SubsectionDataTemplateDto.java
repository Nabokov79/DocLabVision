package ru.nabokovsg.temlservice.dto.subsection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class SubsectionDataTemplateDto {

    @Schema(description = "Индентификатор")
    private long id;
    @Schema(description = "Данные подраздела")
    private String subsectionData;
}