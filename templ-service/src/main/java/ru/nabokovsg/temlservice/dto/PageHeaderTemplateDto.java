package ru.nabokovsg.temlservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные заголовка листп протокола, заключения")
public class PageHeaderTemplateDto {

    @Schema(description = "Индентификатор")
    private long id;
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
}