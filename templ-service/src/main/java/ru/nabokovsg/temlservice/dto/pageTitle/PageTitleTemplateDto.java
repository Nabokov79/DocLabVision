package ru.nabokovsg.temlservice.dto.pageTitle;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.temlservice.dto.header.PageHeaderTemplateDto;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные титульного листа отчета, протокола, заключения")
public class PageTitleTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Заголовок титульной страницы")
    private PageHeaderTemplateDto header;
    @Schema(description = "Заголовок документа")
    private String documentName;
    @Schema(description = "Название документа")
    private String documentTitle;
    @Schema(description = "Строка наименования объекта")
    private String objectString;
    @Schema(description = "Строка наименования места расположения объекта")
    private String installationLocationString;
    @Schema(description = "Строка указания адреса")
    private String addressString;
    @Schema(description = "Подпись сотрудника")
    private String signatureString;
    @Schema(description = "Населенный пункт")
    private String city;
    @Schema(description = "Год выдачи документа")
    private String year;
}