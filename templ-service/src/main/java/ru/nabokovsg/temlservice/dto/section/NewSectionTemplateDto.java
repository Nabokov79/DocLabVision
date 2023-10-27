package ru.nabokovsg.temlservice.dto.section;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные нового раздела документа")
public class NewSectionTemplateDto {

    @Schema(description = "Порядковый номер раздела документа")
    @NotNull(message = "sequential section number should not be null")
    @Positive(message = "sequential section number must be positive")
    private Integer sequentialSectionNumber;
    @Schema(description = "Название раздела документа")
    @NotBlank(message = "section name should not be blank")
    private String sectionName;
}