package ru.nabokovsg.temlservice.dto.appendices;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные нового приложения к отчету, протоколу, заключению")
public class NewAppendicesTemplateDto {
}
