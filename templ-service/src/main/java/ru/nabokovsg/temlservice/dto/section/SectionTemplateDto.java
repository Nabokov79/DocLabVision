package ru.nabokovsg.temlservice.dto.section;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.temlservice.dto.subsection.SubsectionTemplateDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные раздела документа")
public class SectionTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Порядковый номер раздела")
    private Integer sequentialSectionNumber;
    @Schema(description = "Название раздела")
    private String sectionName;
    @Schema(description = "Подразделы")
    private List<SubsectionTemplateDto> subsections;
//    @Schema(description = "Протоколы раздела документа")
//    private List<ProtocolTemplateDto> protocolTemplates;
//    @Schema(description = "Рекомендации для объекта обследования")
//    private List<RecommendationTemplateDto> recommendations;
//    @Schema(description = "Приложения документа")
//    private List<AppendicesTemplateDto> appendices;
}