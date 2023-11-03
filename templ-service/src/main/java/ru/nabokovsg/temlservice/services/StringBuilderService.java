package ru.nabokovsg.temlservice.services;

import ru.nabokovsg.temlservice.dto.client.*;

import java.util.List;

public interface StringBuilderService {

    String  licenseToString(LicenseDto license);

    String  requisitesToString(RequisitesDto requisites);

    String addressToString(AddressDto address);

    String signatureEmployeeString(EmployeeDto employee);

    String documentTitleToString(AddressDto address);

   String documentationToString(DocumentationDto documentations);

   String organizationToStrong(OrganizationDto organization);

   String branchToString(BranchDto branch);

   String departmentToString(DepartmentDto department);
}