package ru.nabokovsg.temlservice.dto.client;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Schema(description = "Краткие данные подразделения филиала организации")
public class DepartmentDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Полное название подразделения филиала организации")
    private String department;
    @Schema(description = "Краткое название подразделения филиала организации")
    private String shortNameDepartment;
    @Schema(description = "Номер подразделения филиала организации")
    private Integer departmentNumber;
    @Schema(description = "Котельная, ЦТП")
    private List<BuildingDto> buildings;
    @Schema(description = "Лицензия, аттестация")
    private List<LicenseDto> licenses;
    @Schema(description = "Реквизиты подразделения филиала организации")
    private RequisitesDto requisites;
}