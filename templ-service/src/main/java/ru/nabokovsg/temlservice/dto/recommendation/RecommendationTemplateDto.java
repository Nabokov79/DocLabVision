package ru.nabokovsg.temlservice.dto.recommendation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные рекомендаций для объектов обследования")
public class RecommendationTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Текст рекомендации")
    private String recommendationText;
}
