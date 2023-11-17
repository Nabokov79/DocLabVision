package ru.nabokovsg.temlservice.services.converter;

import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.models.TemplateData;
import ru.nabokovsg.temlservice.dto.client.*;
import ru.nabokovsg.temlservice.exceptions.BadRequestException;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StringFactoryImpl implements StringFactory {

    @Override
    public String create(TemplateData data) {
        switch (data.getType()) {
            case LICENSE -> {return  convertLicense(data.getLicenses());}
            case REQUISITES -> {return convertRequisites(data.getRequisites());}
            case SIGNATURE -> {return convertEmployee(data.getEmployee());}
            case ORGANIZATION -> { return convertOrganization(data.getOrganization());}
            case BRANCH -> {return convertBranch(data.getDivisionName(), data.getBranch());}
            case DEPARTMENT -> {return convertDepartment(data.getDivisionName(), data.getDepartment());}
            case DOCUMENTATION -> {return convertDocumentation(data.getDocument());}
            default -> throw new BadRequestException(String.format("data type=%s is not supported", data.getType()));
        }
    }

    private String convertDocumentation(DocumentationDto documentations) {
        String string = String.join("", "«", documentations.getTitle(), "»");
        if (documentations.getNumber() != null) {
            string = String.join(" ", documentations.getNumber(), string);
        }
        if (documentations.getView() != null) {
            string = String.join(" ", documentations.getView(), string);
        }
        return string;
    }

    private String convertLicense(List<LicenseDto> licenses) {
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

    private String convertRequisites(RequisitesDto requisites) {
        return String.join("//"
        , String.join(", ", String.valueOf(requisites.getIndex()), convertAddress(requisites.getAddress()))
        , String.join(" ","тел./факс", requisites.getPhone(),"/", requisites.getFax())
        , String.join(" ", "E-mail:", requisites.getEmail()));
    }

    private String convertToShortRequisites(RequisitesDto requisites) {
        return String.join(", ", String.valueOf(requisites.getIndex()),  convertAddress(requisites.getAddress()));
    }

    private String convertAddress(AddressDto address) {
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

    private String convertEmployee(EmployeeDto employee) {
        return String.join("/"
                , employee.getPost()
                , String.join(". ", String.join(".", String.valueOf(employee.getName().charAt(0))
                                                                   , String.valueOf(employee.getPatronymic().charAt(0)))
                                                                    .toUpperCase()
                                         , employee.getSurname()));
    }

    private String convertOrganization(OrganizationDto organization) {
        return String.join(". ", organization.getOrganization()
                , convertToShortRequisites(organization.getRequisites())
                , convertLicense(organization.getLicenses()));
    }

    private String convertBranch(String divisionName, BranchDto branch) {
        String name = branch.getBranch();
        if (divisionName != null) {
            name = divisionName;
        }
        return String.join(". ", name
                , convertToShortRequisites(branch.getRequisites())
                , convertLicense(branch.getLicenses()));
    }

    private String convertDepartment(String divisionName, DepartmentDto department) {
        String name = department.getDepartment();
        if (divisionName != null) {
            name = divisionName;
        }
        return String.join(". ", name
                , convertToShortRequisites(department.getRequisites())
                , convertLicense(department.getLicenses()));
    }
}