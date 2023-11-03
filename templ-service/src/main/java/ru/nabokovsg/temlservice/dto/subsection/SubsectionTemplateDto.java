package ru.nabokovsg.temlservice.dto.subsection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.temlservice.dto.recommendation.RecommendationTemplateDto;
import ru.nabokovsg.temlservice.dto.tableTemlate.TableTemplateDto;
import ru.nabokovsg.temlservice.dto.сonclusion.ConclusionTemplateDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные подраздела раздела отчета, рподаздела протокола, подраздела заключения")
public class SubsectionTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Порядковый номер подраздела")
    private double sequentialSubsectionNumber;
    @Schema(description = "Название подраздела")
    private String subsectionName;
    @Schema(description = "Текст подраздела")
    private String subsectionText;
    @Schema(description = "Тип данных пораздела")
    private String subsectionDataType;
    @Schema(description = "Шаблон таблиц")
    private List<TableTemplateDto> tablesTemplate;
    @Schema(description = "Шаблон рекомендаций")
    private List<RecommendationTemplateDto> recommendations;
    @Schema(description = "Шаблон заключений")
    private ConclusionTemplateDto conclusionsTemplate;
    @Schema(description = "Показать номер подраздела в документе")
    private boolean subsectionNumber;
}