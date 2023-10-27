package ru.nabokovsg.temlservice.dto.pageTitle;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.temlservice.dto.PageHeaderTemplateDto;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные титульного листа отчета, протокола, заключения")
public class PageTitleTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    private PageHeaderTemplateDto pageHeaderTemplate;
    @Schema(description = "Строка наименования объекта")
    private String objectString;
    @Schema(description = "Строка наименования места расположения объекта")
    private String installationLocationString;
    @Schema(description = "Строка указания адреса")
    private String addressString;
    @Schema(description = "Подпись сотрудника")
    private String employee;
    @Schema(description = "Населенный пункт")
    private String city;
    @Schema(description = "Год выдачи документа")
    private String year;
}