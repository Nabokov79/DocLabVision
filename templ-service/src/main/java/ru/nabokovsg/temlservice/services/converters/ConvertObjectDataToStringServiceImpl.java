package ru.nabokovsg.temlservice.services.converters;

import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.builders.TemplateData;
import ru.nabokovsg.temlservice.dto.client.*;
import ru.nabokovsg.temlservice.exceptions.BadRequestException;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ConvertObjectDataToStringServiceImpl implements ConvertObjectDataToStringService {

    @Override
    public String createString(TemplateData data) {
        switch (data.getDataType()) {
            case LICENSE -> {return licenseToString(data.getLicenses());}
            case REQUISITES -> {return requisitesToString(data.getRequisites());}
            case SHORT_REQUISITES -> {return shortRequisitesToString(data.getRequisites());}
            case ADDRESS -> {return addressToString(data.getAddress());}
            case SIGNATURE -> {return signatureEmployeeString(data.getEmployee());}
            case DOCUMENTATION -> {return documentationToString(data.getDocumentations());}
            case ORGANIZATION -> {return organizationToStrong(data.getOrganization());}
            case BRANCH -> {return branchToString(data.getDivisionName(), data.getBranch());}
            case DEPARTMENT -> {return departmentToString(data.getDivisionName(), data.getDepartment());}
            default -> throw new BadRequestException(String.format("data type=%s is not supported", data.getType()));
        }
    }

    private String licenseToString(List<LicenseDto> licenses) {
        LicenseDto license = licenses.stream()
                .filter(l -> l.getEndData().isAfter(LocalDate.now()))
                .toList()
                .get(0);
        if (license == null) {
            license = licenses.stream()
                    .max(Comparator.comparing(LicenseDto::getEndData))
                    .orElseThrow(NoSuchElementException::new);
        }
        return String.join(" ", license.getDocumentType(),
                                                  "№",
                                                  license.getLicenseNumber(),
                                                  "от",
                                                  license.getStartData().toString());
    }

    private String requisitesToString(RequisitesDto requisites) {
        return String.join("//"
        , String.join(" ", String.valueOf(requisites.getIndex()), addressToString(requisites.getAddress()))
        , String.join(" ","тел./факс", requisites.getPhone(),"/", requisites.getFax())
        , String.join(" ", "E-mail:", requisites.getEmail()));
    }

    private String shortRequisitesToString(RequisitesDto requisites) {
        return String.join(", ", String.valueOf(requisites.getIndex()), addressToString(requisites.getAddress()));
    }

    private String addressToString(AddressDto address) {
        String string = String.join(", ", address.getCity()
                                                , String.join(" ", address.getStreet()
                                                                            , "д.", String.valueOf(address.getHouseNumber())));
        if (address.getBuildingNumber() != null) {
            string = String.join(", ", string, String.join("", "корп.", String.valueOf(address.getBuildingNumber())));
        }
        if (address.getLetter() != null) {
            string = String.join(", ", string, String.join("", "лит.", address.getLetter()));
        }
        return string;
    }

    private String signatureEmployeeString(EmployeeDto employee) {
        return String.join("/"
                , employee.getPost()
                , String.join(". ", String.join(".", String.valueOf(employee.getName().charAt(0))
                                                                   , String.valueOf(employee.getPatronymic().charAt(0)))
                                                                    .toUpperCase()
                                         , employee.getSurname()));
    }

    private String documentationToString(DocumentationDto documentations) {
        String string = String.join("", "«", documentations.getTitle(), "»");
        if (documentations.getNumber() != null) {
            string = String.join(" ", documentations.getNumber(), string);
        }
        if (documentations.getView() != null) {
            string = String.join(" ", documentations.getView(), string);
        }
        return string;
    }

    private String organizationToStrong(OrganizationDto organization) {
        return String.join(". ", organization.getOrganization()
                , shortRequisitesToString(organization.getRequisites())
                , licenseToString(organization.getLicenses()));
    }

    private String branchToString(String divisionName, BranchDto branch) {
        String name = branch.getBranch();
        if (divisionName != null) {
            name = divisionName;
        }
        return String.join(". ", name
                , shortRequisitesToString(branch.getRequisites())
                , licenseToString(branch.getLicenses()));
    }

    private String departmentToString(String divisionName, DepartmentDto department) {
        String name = department.getDepartment();
        if (divisionName != null) {
            name = divisionName;
        }
        return String.join(". ", name
                , shortRequisitesToString(department.getRequisites())
                , licenseToString(department.getLicenses()));
    }
}