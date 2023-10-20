package ru.nabokovsg.temlservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.client.TemplateClient;
import ru.nabokovsg.temlservice.dto.client.*;
import ru.nabokovsg.temlservice.dto.pageTitle.NewPageTitleDto;
import ru.nabokovsg.temlservice.models.PageTitle;
import ru.nabokovsg.temlservice.repository.PageTitleRepository;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PageTitleServiceImpl implements PageTitleService {

    private final PageTitleRepository repository;
    private final TemplateClient client;
    private final StringBuilderService stringBuilderService;

    @Override
    public PageTitle save(NewPageTitleDto pageTitleDto) {
        OrganizationDto organization = client.getOrganization(pageTitleDto.getOrganizationId());
        Map<Long, BranchDto> branches = organization.getBranches().stream()
                .collect(Collectors.toMap(BranchDto::getId, b -> b));
        Map<Long, DepartmentDto> departments = organization.getBranches()
                .stream()
                .map(BranchDto::getDepartments)
                .flatMap(Collection::stream)
                .collect(Collectors.toMap(DepartmentDto::getId, d -> d));
        Map<Long, LicenseDto> licenses = Stream.of(
                organization.getLicenses()
              , organization.getBranches().stream()
                                .map(BranchDto::getLicenses)
                                .flatMap(Collection::stream)
                                .toList()
              , organization.getBranches().stream()
                                .map(BranchDto::getDepartments)
                                .flatMap(Collection::stream)
                                .map(DepartmentDto::getLicenses)
                                .flatMap(Collection::stream)
                                .toList())
                .flatMap(Collection::stream)
                .collect(Collectors.toMap(LicenseDto::getId, l -> l));
        PageTitle pageTitle = new PageTitle();
        if (pageTitleDto.isOrganizationFullName()) {
            pageTitle.setOrganization(organization.getOrganization());
        } else {
            pageTitle.setOrganization(organization.getShortNameOrganization());
        }
        if (pageTitleDto.getOrganizationLicenseId() != null) {
            pageTitle.setOrganizationLicense(stringBuilderService.licenseToString(licenses.get(pageTitleDto.getOrganizationLicenseId())));
        }
        if (pageTitleDto.isBranchFullName()) {
            pageTitle.setBranch(branches.get(pageTitleDto.getBranchId()).getBranch());
        } else {
            pageTitle.setBranch(branches.get(pageTitleDto.getBranchId()).getShortNameBranch());
        }
        if (pageTitleDto.getBranchLicenseId() != null) {
            pageTitle.setOrganizationLicense(stringBuilderService.licenseToString(licenses.get(pageTitleDto.getBranchLicenseId())));
        }
        if (pageTitleDto.isDepartmentFullName()) {
            pageTitle.setDepartment(departments.get(pageTitleDto.getDepartmentId()).getDepartment());
        } else {
            pageTitle.setDepartment(departments.get(pageTitleDto.getDepartmentId()).getShortNameDepartment());
        }
        if (pageTitleDto.getDepartmentLicenseId() != null) {
            pageTitle.setOrganizationLicense(stringBuilderService.licenseToString(licenses.get(pageTitleDto.getDepartmentLicenseId())));
        }
        if (pageTitleDto.isOrganizationRequisites()) {
            pageTitle.setOrganizationRequisites(stringBuilderService.requisitesToString(organization.getRequisites()));
        }
        if (pageTitleDto.isBranchRequisites()) {
            pageTitle.setBranchRequisites(stringBuilderService.requisitesToString(branches.get(pageTitleDto.getBranchId()).getRequisites()));
        }
        if (pageTitleDto.isDepartmentRequisites()) {
            pageTitle.setDepartmentRequisites(stringBuilderService.requisitesToString(departments.get(pageTitleDto.getDepartmentId()).getRequisites()));
        }
        return repository.save(pageTitle);
    }
}