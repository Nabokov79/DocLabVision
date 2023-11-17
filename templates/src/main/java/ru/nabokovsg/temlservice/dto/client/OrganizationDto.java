package ru.nabokovsg.temlservice.dto.client;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Schema(description = "Данные о организации")
public class OrganizationDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Полное наименование организации")
    private String organization;
    @Schema(description = "Краткое наименование организации")
    private String shortNameOrganization;
    @Schema(description = "Лицензия, аттестация")
    private List<LicenseDto> licenses;
    @Schema(description = "Филиалы организации")
    private List<BranchDto> branches;
    @Schema(description = "Реквизиты")
    private RequisitesDto requisites;
}