package ru.nabokovsg.dataservice.dto.documentation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Данные нового автора проекта")
public class NewDocumentationDto {

    @Schema(description = "Вид документа")
    private String view;
    @Schema(description = "Номер документа")
    private String number;
    @Schema(description = "Заголовок документа")
    @NotBlank(message = "title should not be blank")
    private String title;
    @Schema(description = "Методический документ")
    @NotNull(message = "methodologicalDocument should not be null")
    private Boolean methodologicalDocument;
    @Schema(description = "Нормативный документа")
    @NotNull(message = "regulatoryDocument should not be null")
    private Boolean regulatoryDocument;
}