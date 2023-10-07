package ru.nabokovsg.dataservice.dto.measuringTool;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class MeasuringToolIdsDto {

    private Long organizationId;
    private Long employeeId;
    private Long manufacturerId;
    private Long ownerId;
}
