package ru.nabokovsg.temlservice.dto.reportProtocol;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import ru.nabokovsg.temlservice.dto.subsection.ProtocolSubsectionTemplateDto;
import ru.nabokovsg.temlservice.dto.table.TableTemplateDto;
import ru.nabokovsg.temlservice.models.ConclusionTemplate;
import ru.nabokovsg.temlservice.models.enums.ProtocolType;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные шаблона протокола отчета")
public class ReportProtocolTemplateDto {

    @Schema(description = "Индентификатор")
    private long id;
    @Schema(description = "Тип протокола")
    private ProtocolType protocolType;
    @Schema(description = "Порядковый номер протокола")
    private Integer sequentialProtocolNumber;
    @Schema(description = "Название документа")
    private String protocolName;
    @Schema(description = "Заголовок протокола")
    private String protocolTitle;
    @Schema(description = "Текст в протоколе")
    private String protocolText;
    @Schema(description = "Заключения")
    private ConclusionTemplate conclusions;
    @Schema(description = "Подразделы протокола")
    private List<ProtocolSubsectionTemplateDto> subsections;
    @Schema(description = "Таблицы протокола")
    private List<TableTemplateDto> tables;
}