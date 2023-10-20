package ru.nabokovsg.dataservice.dto.department;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.building.BuildingDto;
import ru.nabokovsg.dataservice.dto.license.LicenseDto;
import ru.nabokovsg.dataservice.dto.requisites.RequisitesDto;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные подразделения филиала организации")
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