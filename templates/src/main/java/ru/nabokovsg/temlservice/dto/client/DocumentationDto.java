package ru.nabokovsg.temlservice.dto.client;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные автора проекта")
public class DocumentationDto {

    @Schema(description = "Индентификатор")
    private Long id;
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