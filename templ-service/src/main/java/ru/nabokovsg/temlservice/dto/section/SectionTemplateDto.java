package ru.nabokovsg.temlservice.dto.section;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.temlservice.dto.appendices.AppendicesTemplateDto;
import ru.nabokovsg.temlservice.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.temlservice.dto.recommendation.RecommendationTemplateDto;
import ru.nabokovsg.temlservice.dto.subsection.SubsectionTemplateDto;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные раздела документа")
public class SectionTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Порядковый номер раздела документа")
    private Integer sequentialSectionNumber;
    @Schema(description = "Название раздела документа")
    private String sectionName;
    @Schema(description = "Подразделы раздела документа")
    private List<SubsectionTemplateDto> subsectionsTemplates;
    @Schema(description = "Протоколы раздела документа")
    private List<ProtocolTemplateDto> protocolTemplates;
    @Schema(description = "Рекомендации для объекта обследования")
    private List<RecommendationTemplateDto> recommendations;
    @Schema(description = "Приложения документа")
    private List<AppendicesTemplateDto> appendices;
}
