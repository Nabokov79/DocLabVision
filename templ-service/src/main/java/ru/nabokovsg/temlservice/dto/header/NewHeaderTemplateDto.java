package ru.nabokovsg.temlservice.dto.header;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные нового заголовка протокола, заключения")
public class NewHeaderTemplateDto {

    @Schema(description = "Индентификатор типа объекта")
    @NotNull(message = "object type id should not be null")
    @Positive(message = "object type id must be positive")
    private Long objectsTypeId;
    @Schema(description = "Индентификатор типа отчетного документа")
    @NotNull(message = "reporting document id should not be null")
    @Positive(message = "reporting document id must be positive")
    private Long reportingDocumentId;
    @Schema(description = "Индентификатор организации")
    @NotNull(message = "organization id should not be null")
    @Positive(message = "organization id can only be positive")
    private Long organizationId;
    @Schema(description = "Указать в документе полное название организации")
    @NotNull(message = "organization full name should not be null")
    private Boolean organizationFullName;
    @Schema(description = "Указать лицензию организации")
    @NotNull(message = "organization license should not be null")
    private Boolean organizationLicense;
    @Schema(description = "Указать контактные данные организации")
    @NotNull(message = "organization requisites should not be null")
    private Boolean organizationRequisites;
    @Schema(description = "Индентификатор филиала организации")
    @NotNull(message = "branch id should not be null")
    @Positive(message = "branch id can only be positive")
    private Long branchId;
    @Schema(description = "Указать позное название филиала")
    @NotNull(message = "branch full name should not be null")
    private Boolean branchFullName;
    @Schema(description = "Указать контактные данные филиала")
    @NotNull(message = "branch requisites should not be null")
    private Boolean branchRequisites;
    @Schema(description = "Указать лицензию филиала организации")
    @NotNull(message = "branch license should not be null")
    private Boolean branchLicense;
    @Schema(description = "Индентификатор подразделения филиала организации")
    @NotNull(message = "department id should not be null")
    @Positive(message = "department id can only be positive")
    private Long departmentId;
    @Schema(description = "Указать полное название подразделения")
    @NotNull(message = "department full name should not be null")
    private Boolean departmentFullName;
    @Schema(description = "Указать контактные данные подразделения")
    @NotNull(message = "department requisites should not be null")
    private Boolean departmentRequisites;
    @Schema(description = "Указать лицензию подразделения")
    @NotNull(message = "department license should not be null")
    private Boolean departmentLicense;
}