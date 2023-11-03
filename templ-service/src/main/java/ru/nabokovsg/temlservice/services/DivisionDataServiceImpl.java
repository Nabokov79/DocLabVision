package ru.nabokovsg.temlservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.client.TemplateClient;
import ru.nabokovsg.temlservice.dto.client.BranchDto;
import ru.nabokovsg.temlservice.dto.client.DepartmentDto;
import ru.nabokovsg.temlservice.dto.client.LicenseDto;
import ru.nabokovsg.temlservice.dto.client.OrganizationDto;
import ru.nabokovsg.temlservice.enums.DivisionType;
import ru.nabokovsg.temlservice.models.SubsectionDataTemplate;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class DivisionDataServiceImpl implements DivisionDataService {

    private final StringBuilderService stringBuilderService;

    private String getOrganizationData(OrganizationDto organization) {
        return String.join(" ", stringBuilderService.organizationToStrong(organization)
                , stringBuilderService.licenseToString(getLicenseData(organization.getLicenses())));
    }

    private String getBranchData(BranchDto branch) {
        return String.join(" ", stringBuilderService.branchToString(branch)
                , stringBuilderService.licenseToString(getLicenseData(branch.getLicenses())));
    }

    private String getDepartmentData(DepartmentDto department) {
        return String.join(" ", stringBuilderService.departmentToString(department)
                , stringBuilderService.licenseToString(getLicenseData(department.getLicenses())));
    }

    private LicenseDto getLicenseData(List<LicenseDto> licenses) {
        LicenseDto license = licenses.stream()
                .filter(l -> l.getEndData().isAfter(LocalDate.now()))
                .toList()
                .get(0);
        if (license == null) {
            return licenses.stream()
                    .max(Comparator.comparing(LicenseDto::getEndData))
                    .orElseThrow(NoSuchElementException::new);
        }
        return license;
    }
}
