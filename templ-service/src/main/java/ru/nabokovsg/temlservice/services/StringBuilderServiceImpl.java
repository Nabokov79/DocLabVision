package ru.nabokovsg.temlservice.services;

import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.dto.client.*;

@Service
public class StringBuilderServiceImpl implements StringBuilderService {

    @Override
    public String licenseToString(LicenseDto license) {
        return String.join(" ", license.getDocumentType(),
                                                  "№",
                                                  license.getLicenseNumber(),
                                                  "от",
                                                  license.getStartData().toString());
    }

    @Override
    public String requisitesToString(RequisitesDto requisites) {
        return String.join("//"
        , String.join(" ", String.valueOf(requisites.getIndex()), addressToString(requisites.getAddress()))
        , String.join(" ","тел./факс", requisites.getPhone(),"/", requisites.getFax())
        , String.join(" ", "E-mail:", requisites.getEmail()));
    }

    @Override
    public String addressToString(AddressDto address) {
        String string = String.join(", ", address.getCity()
                                                , address.getStreet()
                                                , String.valueOf(address.getHouseNumber()));
        if (address.getBuildingNumber() != null) {
            string = String.join(", ", string, "корп.", String.valueOf(address.getBuildingNumber()));
        }
        if (address.getLetter() != null) {
            string = String.join(", ", string, "лит.", address.getLetter());
        }
        return string;
    }

    @Override
    public String signatureEmployeeString(EmployeeDto employee) {
        return String.join("/"
                , employee.getPost()
                , String.join(". ", String.join(".", String.valueOf(employee.getName().charAt(0))
                                                                   , String.valueOf(employee.getPatronymic().charAt(0)))
                                                                    .toUpperCase()
                                         , employee.getSurname()));
    }

    @Override
    public String documentTitleToString(AddressDto address) {
        return null;
    }

    @Override
    public String documentationToString(DocumentationDto documentations) {
        return String.join(" ", documentations.getView()
                                      , documentations.getNumber()
                                      , String.join("", "«", documentations.getTitle(), "»"));
    }

    @Override
    public String organizationToStrong(OrganizationDto organization) {
        return null;
    }

    @Override
    public String branchToString(BranchDto branch) {
        return null;
    }

    @Override
    public String departmentToString(DepartmentDto department) {
        return null;
    }
}