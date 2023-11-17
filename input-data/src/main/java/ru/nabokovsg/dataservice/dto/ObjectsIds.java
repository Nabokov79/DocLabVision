package ru.nabokovsg.dataservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ObjectsIds {

    private Long objectsTypeId;
    private Long buildingId;
    private Long departmentId;
    private Long addressId;
    private Long organizationId;
    private Long employeeId;
    private Long toolOwnerId;
    private Long manufacturerId;
    private Long branchId;
    private Long issuedLicenseId;
    private Long surveyObjectId;
    private Long workPerformedId;
    private Long reportingDocumentId;
}