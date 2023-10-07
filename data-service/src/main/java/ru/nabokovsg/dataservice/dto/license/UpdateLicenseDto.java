package ru.nabokovsg.dataservice.dto.license;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения иформации о лицензии")
public class UpdateLicenseDto {

    @Schema(description = "Индентификатор лицензии")
    @NotNull(message = "id should not be blank")
    @Positive(message = "license id can only be positive")
    private Long id;
    @Schema(description = "Индентификатор организации")
    @NotNull(message = "organization id should not be blank")
    @Positive(message = "organization id can only be positive")
    private Long organizationId;
    @Schema(description = "Индентификатор филиала организации")
    @NotNull(message = "branch id should not be blank")
    @Positive(message = "branch id can only be positive")
    private Long branchId;
    @Schema(description = "Индентификатор структурного подразделения филиала организации")
    @NotNull(message = "department id should not be blank")
    @Positive(message = "department id can only be positive")
    private Long departmentId;
    @Schema(description = "Вид документа")
    @NotBlank(message = "document should not be blank")
    private String documentType;
    @Schema(description = "Номер свидетельства")
    @NotBlank(message = "license number should not be blank")
    private String licenseNumber;
    @Schema(description = "Дата выдачи лицензии")
    @NotNull(message = "start date license should not be blank")
    private LocalDate startData;
    @Schema(description = "Дата окончания действия свидетельства")
    @NotNull(message = "end date license should not be blank")
    private LocalDate endData;
    @Schema(description = "Индентификатор организации выдавшей лицензию")
    @NotNull(message = "issuedLicense id should not be blank")
    private Long issuedLicenseId;
}