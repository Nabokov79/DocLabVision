package ru.nabokovsg.temlservice.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.dto.client.*;

@Service
@RequiredArgsConstructor
public class TemplateClient {

    private final DataServiceClient client;

    public OrganizationDto getOrganization(Long id) {
        return client.getOrganization("/data/organizations/" + id);
    }

    public BranchDto getBranch(Long id) {
        return client.getBranch("/data/organizations/branch/" + id);
    }

    public DepartmentDto getDepartment(Long id) {
        return client.getDepartment("/data/organizations/branch/department/" + id);
    }

    public ReportingDocumentDto getReportingDocument(Long id){
        return client.getReportingDocument("/data/applications/document/" + id);
    }

    public ObjectsTypeDto getObjectsType(Long id) {
        return client.getObjectsType("/data/objects/type/" + id);
    }

    public EmployeeDto getEmployee(Long id) {
        return client.getEmployee("/data/employee/" + id);
    }
}