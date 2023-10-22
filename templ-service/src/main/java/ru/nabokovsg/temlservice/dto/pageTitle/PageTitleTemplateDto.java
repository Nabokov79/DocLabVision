package ru.nabokovsg.temlservice.dto.pageTitle;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные титульного листа отчета, протокола, заключения")
public class PageTitleTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Организация")
    private String organization;
    @Schema(description = "Лицензия организации")
    private String organizationLicense;
    @Schema(description = "Контактные данные организации")
    private String organizationRequisites;
    @Schema(description = "Филиал организации")
    private String branch;
    @Schema(description = "Контактные данные филиала организации")
    private String branchRequisites;
    @Schema(description = "Лицензия филиала организации")
    private String licenseBranch;
    @Schema(description = "Подразделение филиала организации")
    private String department;
    @Schema(description = "Контактные данные подразделения филиала организации")
    private String departmentRequisites;
    @Schema(description = "Лицензия подразделения филиала организации")
    private String departmentLicense;
    @Schema(description = "Название документа")
    private String documentName;
    @Schema(description = "Заголовок документа")
    private String documentTitle;
}