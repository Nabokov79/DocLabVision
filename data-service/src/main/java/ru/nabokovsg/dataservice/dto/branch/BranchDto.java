package ru.nabokovsg.dataservice.dto.branch;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.department.DepartmentDto;
import ru.nabokovsg.dataservice.dto.license.LicenseDto;
import ru.nabokovsg.dataservice.dto.requisites.RequisitesDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные нового подразделения")
public class BranchDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Полное название филиала организации")
    private String branch;
    @Schema(description = "краткое название филиала организации")
    private String shortNameBranch;
    @Schema(description = "Лицензия, аттестация")
    private List<LicenseDto> licenses;
    @Schema(description = "Реквизиты филиала организации")
    private List<DepartmentDto> departments;
    @Schema(description = "Реквизиты филиала организации")
    private RequisitesDto requisites;
}