package ru.nabokovsg.temlservice.dto.protocol;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.temlservice.dto.appendices.AppendicesTemplateDto;
import ru.nabokovsg.temlservice.dto.pageTitle.PageTitleTemplateDto;
import ru.nabokovsg.temlservice.dto.subsection.SubsectionTemplateDto;
import ru.nabokovsg.temlservice.dto.tableTemlate.TableTemplateDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные шаблона протокола")
public class ProtocolTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Шаблон заголовка страницы протокола")
    private PageTitleTemplateDto pageHeader;
    @Schema(description = "Шаблон подраздела протокола")
    private List<SubsectionTemplateDto> subsectionsTemplate;
    @Schema(description = "Шаблон таблиц протокола")
    private List<TableTemplateDto> tablesHeaderTemplate;
    @Schema(description = "Шаблон приложений протокола")
    private List<AppendicesTemplateDto> appendices;
}