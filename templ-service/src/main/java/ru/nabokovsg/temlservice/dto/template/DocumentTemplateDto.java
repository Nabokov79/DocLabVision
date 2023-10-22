package ru.nabokovsg.temlservice.dto.template;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import ru.nabokovsg.temlservice.dto.pageTitle.PageTitleTemplateDto;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные протокола")
public class DocumentTemplateDto {

    @Schema(description = "Титульная страница отчета")
    private PageTitleTemplateDto pageTitle;
}