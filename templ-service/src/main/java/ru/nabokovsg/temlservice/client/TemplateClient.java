package ru.nabokovsg.temlservice.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.dto.client.EmployeeDto;
import ru.nabokovsg.temlservice.dto.client.ObjectsTypeDto;
import ru.nabokovsg.temlservice.dto.client.OrganizationDto;
import ru.nabokovsg.temlservice.dto.client.ReportingDocumentDto;

@Service
@RequiredArgsConstructor
public class TemplateClient {

    private final DataServiceClient client;

    public OrganizationDto getOrganization(Long id) {
        return client.getOrganization("/data/organizations/" + id);
    }

    public ReportingDocumentDto getReportingDocument(Long id){
        return client.getReportingDocument("/data/applications/document/" + id);
    }

    public ObjectsTypeDto getObjectsType(Long id) {
        return client.getObjectsType("/data/objects/type" + id);
    }

    public EmployeeDto getEmployee(Long id) {
        return client.getEmployee("/data/employee/" + id);
    }
}
