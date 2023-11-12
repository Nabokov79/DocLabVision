package ru.nabokovsg.temlservice.dto.protocol;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.temlservice.dto.header.PageHeaderTemplateDto;
import ru.nabokovsg.temlservice.dto.subsection.SubsectionTemplateDto;
import ru.nabokovsg.temlservice.dto.table.TableTemplateDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные шаблона протокола")
public class ProtocolTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Порядковый номер протокола")
    private Integer sequentialProtocolNumber;
    @Schema(description = "Название документа")
    private String protocolName;
    @Schema(description = "Заголовок протокола")
    private String protocolTitle;
    @Schema(description = "Шаблон заголовка страницы протокола")
    private PageHeaderTemplateDto header;
    @Schema(description = "Подразделы")
    private List<SubsectionTemplateDto> subsections;
    @Schema(description = "Шаблон таблиц")
    private List<TableTemplateDto> tables;
}