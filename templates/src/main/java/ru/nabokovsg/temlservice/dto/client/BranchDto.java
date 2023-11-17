package ru.nabokovsg.temlservice.dto.client;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Schema(description = "Данные нового подразделения")
public class BranchDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Полное название филиала организации")
    private String branch;
    @Schema(description = "Краткое название филиала организации")
    private String shortNameBranch;
    @Schema(description = "Подразделения филиала организации")
    private List<DepartmentDto> departments;
    @Schema(description = "Лицензия, аттестация")
    private List<LicenseDto> licenses;
    @Schema(description = "Реквизиты филиала организации")
    private RequisitesDto requisites;
}