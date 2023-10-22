package ru.nabokovsg.temlservice.dto.report;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.temlservice.dto.pageTitle.PageTitleTemplateDto;
import ru.nabokovsg.temlservice.dto.section.SectionTemplateDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные шаблона отчета")
public class ReportTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Шаблон титульной страницы отчета")
    private PageTitleTemplateDto pageTitle;
    @Schema(description = "Шаблон раздел отчета")
    private List<SectionTemplateDto> sectionTemplates;
}