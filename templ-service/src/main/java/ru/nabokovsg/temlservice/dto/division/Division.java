package ru.nabokovsg.temlservice.dto.division;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.nabokovsg.temlservice.enums.DivisionType;

@Getter
@AllArgsConstructor
public class Division {

    @Schema(description = "Индентификатор структурного подразделения организации")
    private Long divisionId;
    @Schema(description = "Вид структурного подразделения организации")
    private DivisionType divisionType;
    @Schema(description = "Пользовательское название структурного подразделения организации")
    private String divisionName;
}