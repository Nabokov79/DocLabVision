package ru.nabokovsg.temlservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.builders.TemplateData;
import ru.nabokovsg.temlservice.client.TemplateClient;
import ru.nabokovsg.temlservice.dto.client.*;
import ru.nabokovsg.temlservice.dto.pageHeader.NewPageHeaderTemplateDto;
import ru.nabokovsg.temlservice.enums.DataType;
import ru.nabokovsg.temlservice.models.PageHeaderTemplate;
import ru.nabokovsg.temlservice.repository.PageHeaderTemplateRepository;
import ru.nabokovsg.temlservice.services.converters.ConvertObjectDataToStringService;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PageHeaderTemplateServiceImpl implements PageHeaderTemplateService {

    private final PageHeaderTemplateRepository repository;
    private final TemplateClient client;
    private final ConvertObjectDataToStringService stringBuilderService;

    @Override
    public PageHeaderTemplate save(Long reportingDocumentId, NewPageHeaderTemplateDto pageTitleDto) {
        OrganizationDto organization = client.getOrganization(pageTitleDto.getOrganizationId());
        Map<Long, BranchDto> branches = organization.getBranches().stream()
                .collect(Collectors.toMap(BranchDto::getId, b -> b));
        Map<Long, DepartmentDto> departments = organization.getBranches()
                .stream()
                .map(BranchDto::getDepartments)
                .flatMap(Collection::stream)
                .collect(Collectors.toMap(DepartmentDto::getId, d -> d));
        PageHeaderTemplate pageTitle = new PageHeaderTemplate();
        if (pageTitleDto.isOrganizationFullName()) {
            pageTitle.setOrganization(organization.getOrganization());
        } else {
            pageTitle.setOrganization(organization.getShortNameOrganization());
        }
        if (pageTitleDto.getOrganizationLicenseId() != null) {
            pageTitle.setOrganizationLicense(stringBuilderService.createString(new TemplateData.Builder().dataType(DataType.LICENSE).licenses(organization.getLicenses()).build()));
        }
        if (pageTitleDto.isBranchFullName()) {
            pageTitle.setBranch(branches.get(pageTitleDto.getBranchId()).getBranch());
        } else {
            pageTitle.setBranch(branches.get(pageTitleDto.getBranchId()).getShortNameBranch());
        }
        if (pageTitleDto.getBranchLicenseId() != null) {
            pageTitle.setOrganizationLicense(
                    stringBuilderService.createString(new TemplateData.Builder().dataType(DataType.LICENSE).licenses(branches.get(pageTitleDto.getBranchLicenseId()).getLicenses()).build()));
        }
        if (pageTitleDto.isDepartmentFullName()) {
            pageTitle.setDepartment(departments.get(pageTitleDto.getDepartmentId()).getDepartment());
        } else {
            pageTitle.setDepartment(departments.get(pageTitleDto.getDepartmentId()).getShortNameDepartment());
        }
        if (pageTitleDto.getDepartmentLicenseId() != null) {
            pageTitle.setDepartmentLicense(
                   stringBuilderService.createString(new TemplateData.Builder().dataType(DataType.LICENSE).licenses(departments.get(pageTitleDto.getDepartmentId()).getLicenses()).build()));
        }
        if (pageTitleDto.isOrganizationRequisites()) {
            pageTitle.setOrganizationRequisites(stringBuilderService.createString(new TemplateData.Builder().dataType(DataType.REQUISITES).requisites(organization.getRequisites()).build()));
        }
        if (pageTitleDto.isBranchRequisites()) {
            pageTitle.setBranchRequisites(
                    stringBuilderService.createString(new TemplateData.Builder().dataType(DataType.REQUISITES).requisites(branches.get(pageTitleDto.getBranchId()).getRequisites()).build()));
        }
        if (pageTitleDto.isDepartmentRequisites()) {
            pageTitle.setDepartmentRequisites(
              stringBuilderService.createString(new TemplateData.Builder().dataType(DataType.REQUISITES).requisites(departments.get(pageTitleDto.getDepartmentId()).getRequisites()).build()));
        }
        return repository.save(pageTitle);
    }
}