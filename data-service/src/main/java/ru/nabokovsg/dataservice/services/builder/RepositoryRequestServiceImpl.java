package ru.nabokovsg.dataservice.services.builder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.exceptions.BadRequestException;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.models.*;
import ru.nabokovsg.dataservice.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RepositoryRequestServiceImpl implements RepositoryRequestService {

    private final BuildingRepository building;
    private final ObjectsTypeRepository objectsType;
    private final DepartmentRepository department;
    private final AddressRepository address;
    private final ElementRepository element;
    private final ObjectSurveyRepository object;
    private final OrganizationRepository organizations;
    private final EmployeeRepository employees;
    private final BranchRepository branches;
    private final ObjectPassportDataTemplateRepository template;
    private final ReportingDocumentRepository reportingDocument;
    private final ObjectSurveyRepository objectSurvey;

    @Override
    public List<Building> getBuildings(List<Long> ids) {
        if (ids.isEmpty()) {
            throw new BadRequestException(String.format("Buildings Ids list with should not be empty, ids=%s", ids));
        }
        return building.findAllById(valid(ids));
    }

    @Override
    public List<ObjectsType> getObjectsTypes(List<Long> ids) {
        if (ids.isEmpty()) {
            throw new BadRequestException(String.format("ObjectsTypes Ids list with should not be empty, ids=%s", ids));
        }
        return objectsType.findAllById(valid(ids));
    }

    @Override
    public Map<Long, Department> getDepartments(List<Long> ids) {
        if (ids.isEmpty()) {
            throw new BadRequestException(String.format("Departments Ids list with should not be empty, ids=%s", ids));
        }
        return department.findAllById(valid(ids)).stream()
                                                 .collect(Collectors.toMap(Department::getId, d -> d));
    }

    @Override
    public Map<Long, Address> getAddresses(List<Long> ids) {
        if (ids.isEmpty()) {
            throw new BadRequestException(String.format("Addresses Ids list with should not be empty, ids=%s", ids));
        }
        return address.findAllById(valid(ids)).stream().collect(Collectors.toMap(Address::getId, a -> a));
    }

    @Override
    public List<Element> getElement(List<Long> ids) {
        if (ids.isEmpty()) {
            throw new BadRequestException(String.format("Elements Ids list with should not be empty, ids=%s", ids));
        }
        return element.findAllById(valid(ids));
    }

    @Override
    public List<Organization> getOrganizations(List<Long> ids) {
        if (ids.isEmpty()) {
            throw new BadRequestException(String.format("Organizations Ids list with should not be empty, ids=%s", ids));
        }
        return organizations.findAllById(valid(ids));
    }

    @Override
    public List<Employee> getEmployees(List<Long> ids) {
        if (ids.isEmpty()) {
            throw new BadRequestException(String.format("Employees Ids list with should not be empty, ids=%s", ids));
        }
        return employees.findAllById(valid(ids));
    }

    @Override
    public Map<Long, Branch> getBranches(List<Long> ids) {
        if (ids.isEmpty()) {
            throw new BadRequestException(String.format("Branches Ids list with should not be empty, ids=%s", ids));
        }
        return branches.findAllById(valid(ids)).stream()
                                               .collect(Collectors.toMap(Branch::getId, b -> b));
    }

    @Override
    public SurveyObject getSurveyObject(Long id) {
        if (id == null) {
            throw new BadRequestException(String.format("SurveyObject with ids=%s should not be empty", id));
        }
        return object.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Survey object with id=%s not found", id)));
    }

    @Override
    public List<ObjectPassportDataTemplate> getTemplates(List<Long> ids) {
        if (ids.isEmpty()) {
            throw new BadRequestException(String.format("ObjectPassportDataTemplate Ids list with should not be empty, ids=%s", ids));
        }
        return template.findAllById(valid(ids));
    }

    @Override
    public Map<Long, ReportingDocument> getReportingDocuments(List<Long> ids) {
        if (ids.isEmpty()) {
            throw new BadRequestException(String.format("ReportingDocuments Ids list with should not be empty, ids=%s", ids));
        }
        return reportingDocument.findAllById(valid(ids)).stream()
                                                        .collect(Collectors.toMap(ReportingDocument::getId, r -> r));
    }

    @Override
    public Map<Long, SurveyObject> getSurveyObjects(List<Long> ids) {
        if (ids.isEmpty()) {
            throw new BadRequestException(String.format("SurveyObjects Ids list with should not be empty, ids=%s", ids));
        }
        return objectSurvey.findAllById(valid(ids)).stream()
                                                   .collect(Collectors.toMap(SurveyObject::getId, s -> s));
    }

    private List<Long> valid(List<Long> ids) {
        ids = ids.stream().filter(Objects::nonNull)
                          .distinct()
                          .toList();
        if (ids.isEmpty()) {
            return new ArrayList<>();
        }
        return ids;
    }
}