package ru.nabokovsg.temlservice.services;

import ru.nabokovsg.temlservice.dto.client.AddressDto;
import ru.nabokovsg.temlservice.dto.client.EmployeeDto;
import ru.nabokovsg.temlservice.dto.client.LicenseDto;
import ru.nabokovsg.temlservice.dto.client.RequisitesDto;

public interface StringBuilderService {

    String  licenseToString(LicenseDto license);

    String  requisitesToString(RequisitesDto requisites);

    String addressToString(AddressDto address);

    String signatureEmployeeString(EmployeeDto employee);

    String documentTitleToString(AddressDto address);
}