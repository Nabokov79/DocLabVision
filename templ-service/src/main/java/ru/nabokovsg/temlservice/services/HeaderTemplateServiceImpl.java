package ru.nabokovsg.temlservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.client.TemplateClient;
import ru.nabokovsg.temlservice.dto.client.*;
import ru.nabokovsg.temlservice.dto.header.NewHeaderTemplateDto;
import ru.nabokovsg.temlservice.dto.header.NewPageTitleHeaderTemplateDto;
import ru.nabokovsg.temlservice.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.temlservice.mappers.HeaderTemplateMapper;
import ru.nabokovsg.temlservice.mappers.ProtocolTemplateMapper;
import ru.nabokovsg.temlservice.models.*;
import ru.nabokovsg.temlservice.models.enums.DataType;
import ru.nabokovsg.temlservice.repository.HeaderTemplateRepository;
import ru.nabokovsg.temlservice.services.converter.StringFactory;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HeaderTemplateServiceImpl implements HeaderTemplateService {

    private final HeaderTemplateRepository repository;
    private final HeaderTemplateMapper mapper;
    private final ProtocolTemplateService service;
    private final ProtocolTemplateMapper protocolMapper;
    private final StringFactory factory;
    private final TemplateClient client;

    @Override
    public ProtocolTemplateDto save(NewHeaderTemplateDto headerDto) {
        ProtocolTemplate protocol = service.getById(headerDto.getObjectsTypeId()
                                                 , headerDto.getReportingDocumentId());
        if (protocol == null) {
            return service.save(saveHeader(headerDto), headerDto);
        }
        return protocolMapper.mapToProtocolTemplateDto(protocol);
    }

    @Override
    public HeaderTemplate getHeader(NewPageTitleHeaderTemplateDto headerDto) {
        return saveHeader(mapper.mapToNewHeaderTemplateDto(headerDto));
    }


    public HeaderTemplate saveHeader(NewHeaderTemplateDto pageTitleDto) {
        OrganizationDto organization = client.getOrganization(pageTitleDto.getOrganizationId());
        BranchDto branch = organization.getBranches()
                                       .stream()
                                       .collect(Collectors.toMap(BranchDto::getId, b -> b))
                                       .get(pageTitleDto.getBranchId());
         DepartmentDto department = organization.getBranches()
                                                .stream()
                                                .map(BranchDto::getDepartments)
                                                .flatMap(Collection::stream)
                                                .collect(Collectors.toMap(DepartmentDto::getId, d -> d))
                                                .get(pageTitleDto.getDepartmentId());
        HeaderTemplate header = new HeaderTemplate();
        setOrganizationData(header
                          , organization
                          , pageTitleDto.getOrganizationFullName()
                          , pageTitleDto.getOrganizationLicense()
                          , pageTitleDto.getOrganizationRequisites());
        setBranchData(header
                    , branch
                    , pageTitleDto.getBranchFullName()
                    , pageTitleDto.getBranchLicense()
                    , pageTitleDto.getBranchRequisites());
        setDepartmentData(header
                        , department
                        , pageTitleDto.getDepartmentFullName()
                        , pageTitleDto.getDepartmentLicense()
                        , pageTitleDto.getDepartmentRequisites());
        return repository.save(header);
    }

    private void setOrganizationData(HeaderTemplate header, OrganizationDto organization
                                   , boolean fullName, boolean licenses, boolean requisites) {
        if (fullName) {
            header.setOrganization(organization.getOrganization());
        } else {
            header.setOrganization(organization.getShortNameOrganization());
        }
        if (licenses) {
            header.setOrganizationLicense(factory.create(new TemplateData.Builder()
                                                                         .type(DataType.LICENSE)
                                                                         .licenses(organization.getLicenses())
                                                                         .build()));
        }
        if (requisites) {
            header.setOrganizationRequisites(factory.create(new TemplateData.Builder()
                                                                            .type(DataType.REQUISITES)
                                                                            .requisites(organization.getRequisites())
                                                                            .build()));
        }
    }

    private void setBranchData(HeaderTemplate header, BranchDto branch
                             , boolean fullName, boolean licenses, boolean requisites) {
        if (fullName) {
            header.setBranch(branch.getBranch());
        } else {
            header.setBranch(branch.getShortNameBranch());
        }
        if (licenses) {
            header.setOrganizationLicense(factory.create(new TemplateData.Builder()
                                                                         .type(DataType.LICENSE)
                                                                         .licenses(branch.getLicenses())
                                                                         .build()));
        }
        if (requisites) {
            header.setBranchRequisites(factory.create(new TemplateData.Builder()
                                                                      .type(DataType.REQUISITES)
                                                                      .requisites(branch.getRequisites())
                                                                      .build()));
        }
    }

    private void setDepartmentData(HeaderTemplate header, DepartmentDto department
                                 , boolean fullName, boolean licenses, boolean requisites) {
        if (fullName) {
            header.setDepartment(department.getDepartment());
        } else {
            header.setDepartment(department.getShortNameDepartment());
        }
        if (licenses) {
            header.setDepartmentLicense(factory.create(new TemplateData.Builder()
                                                                       .type(DataType.LICENSE)
                                                                       .licenses(department.getLicenses())
                                                                       .build()));
        }
        if (requisites) {
           header.setDepartmentRequisites(factory.create(new TemplateData.Builder()
                                                                         .type(DataType.REQUISITES)
                                                                         .requisites(department.getRequisites())
                                                                         .build()));
        }
    }
}