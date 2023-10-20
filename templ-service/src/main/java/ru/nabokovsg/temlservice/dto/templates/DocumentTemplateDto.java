package ru.nabokovsg.temlservice.dto.templates;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import ru.nabokovsg.temlservice.dto.pageTitle.PageTitleDto;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные протокола")
public class DocumentTemplateDto {

    @Schema(description = "Титульная страница отчета")
    private PageTitleDto pageTitle;
    @Schema(description = "Название отчета")
    private String documentName;
    @Schema(description = "Заголовок отчета")
    private String documentTitle;
}