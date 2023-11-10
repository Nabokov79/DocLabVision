package ru.nabokovsg.temlservice.dto.header;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные заголовка титульной страницы")
public class PageHeaderTemplateDto {

    @Schema(description = "Индентификатор")
    private long id;
    @Schema(description = "Организация")
    private String organization;
    @Schema(description = "Лицензия организации")
    private String organizationLicense;
    @Schema(description = "Лицензия организации")
    private String organizationRequisites;
    @Schema(description = "Филиал организации")
    private String branch;
    @Schema(description = "Реквизиты филиала")
    private String branchRequisites;
    @Schema(description = "Лицензия/аттестация филиала")
    private String licenseBranch;
    @Schema(description = "Подразделение филиала организации")
    private String department;
    @Schema(description = "Реквизиты подразделения")
    private String departmentRequisites;
    @Schema(description = "Лицензия/аттестация подразделения")
    private String departmentLicense;
}