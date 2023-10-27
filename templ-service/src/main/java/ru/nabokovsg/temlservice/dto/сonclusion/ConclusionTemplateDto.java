package ru.nabokovsg.temlservice.dto.сonclusion;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные шаблонов заключений")
public class ConclusionTemplateDto {

    @Schema(description = "Индентификатор")
    private long id;
    @Schema(description = "Текст заключения при приближении замера к предельно допустимому значению")
    private String approachingDefect;
    @Schema(description = "Текст заключения при равенстве замера и предельно допустимомого значения")
    private String equalDefect;
    @Schema(description = "Текст заключения при знечении замера ниже предельно допустимого значени.")
    private String isDefect;
    @Schema(description = "Текст заключения при значении замера выше предельного допустимого значения плюс условный допуск")
    private String isNotDefect;
    @Schema(description = "Текст заключения при отсутствии данных о допустимых нормах брака")
    private String noNormDefect;
}