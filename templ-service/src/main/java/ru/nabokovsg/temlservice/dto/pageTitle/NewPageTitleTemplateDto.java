package ru.nabokovsg.temlservice.dto.pageTitle;

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
@Schema(description = "Данные нового титульного листа отчета, протокола, заключения")
public class NewPageTitleTemplateDto {

    @Schema(description = "Данные шаблона документа(отчет, протокол, заключение")
    @NotNull(message = "template should not be null")
    private NewTemplateDataDto template;
    @Schema(description = "Индентификатор организации")
    @NotNull(message = "organization id should not be null")
    @Positive(message = "organization id can only be positive")
    private Long organizationId;
    @Schema(description = "Указать в документе полное название организации")
    @NotNull(message = "organization full name should not be null")
    private boolean organizationFullName;
    @Schema(description = "Индентификатор лицензии организации")
    private Long organizationLicenseId;
    @Schema(description = "Указать контактные данные организации")
    @NotNull(message = "organization requisites should not be null")
    private boolean organizationRequisites;
    @Schema(description = "Индентификатор филиала организации")
    @NotNull(message = "branch id should not be null")
    @Positive(message = "branch id can only be positive")
    private Long branchId;
    @Schema(description = "Указать позное название филиала организации")
    @NotNull(message = "branch full name should not be null")
    private boolean branchFullName;
    @Schema(description = "Указать контактные данные филиала организации")
    @NotNull(message = "branch requisites should not be null")
    private boolean branchRequisites;
    @Schema(description = "Указать лицензию филиала организации")
    private Long branchLicenseId;
    @Schema(description = "Индентификатор подразделения филиала организации")
    @NotNull(message = "department id should not be null")
    @Positive(message = "department id can only be positive")
    private Long departmentId;
    @Schema(description = "Указать полное название подразделения организации")
    @NotNull(message = "department full name should not be null")
    private boolean departmentFullName;
    @Schema(description = "Указать контактные данные подразделения филиала организации")
    @NotNull(message = "department requisites should not be null")
    private boolean departmentRequisites;
    @Schema(description = "Указать лицензию подразделения филиала организации")
    private Long departmentLicenseId;
}