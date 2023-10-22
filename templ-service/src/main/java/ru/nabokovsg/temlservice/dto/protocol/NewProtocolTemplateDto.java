package ru.nabokovsg.temlservice.dto.protocol;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.temlservice.dto.template.NewTemplateDataDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные нового протокола отчета")
public class NewProtocolTemplateDto {

    @Schema(description = "Данные шаблона документа(отчет, протокол, заключение")
    @NotNull(message = "template should not be null")
    private NewTemplateDataDto template;
    @Schema(description = "Порядковый номер протокола")
    @NotNull(message = "sequential protocol number should not be null")
    @Positive(message = "sequential protocol number must be positive")
    private Integer sequentialProtocolNumber;
}