package ru.nabokovsg.dataservice.dto.documentation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.models.ObjectsType;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные автора проекта")
public class DocumentationDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Тип объекта")
    private ObjectsType objectsType;
    private String view;
    @Schema(description = "Номер документа")
    private String number;
    @Schema(description = "Заголовок документа")
    private String title;
    @Schema(description = "Методический документ")
    private Boolean methodologicalDocument;
    @Schema(description = "Нормативно-технический документ")
    private Boolean regulatoryDocument;
}