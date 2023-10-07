package ru.nabokovsg.dataservice.services.builder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.ObjectsIds;
import ru.nabokovsg.dataservice.exceptions.BadRequestException;
import ru.nabokovsg.dataservice.models.*;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class DataFactory {

    private final RepositoryRequestService service;

    public DataBuilder getBuilder(List<ObjectsIds> ids, BuilderType type) {
        switch (type) {
            case OBJECT -> {
                return new DataBuilder.Data()
                        .buildings(service.getBuildings(ids.stream()
                                .map(ObjectsIds::getBuildingId)
                                .toList()))
                        .objectsTypes(service.getObjectsTypes(ids.stream()
                                .map(ObjectsIds::getObjectsTypeId)
                                .toList()))
                        .build();
            }
            case BUILDING -> {
                return new DataBuilder.Data()
                        .departments(service.getDepartments(ids.stream()
                                .map(ObjectsIds::getDepartmentId)
                                .toList()))
                        .addresses(service.getAddresses(ids.stream()
                                .map(ObjectsIds::getAddressId)
                                .toList()))
                        .build();
            }
            case CERTIFICATE -> {
                return new DataBuilder.Data()
                        .organizations(service.getOrganizations(ids.stream()
                                .map(ObjectsIds::getOrganizationId)
                                .toList())
                                .stream()
                                .collect(Collectors.toMap(Organization::getId, o -> o)))
                        .employees(service.getEmployees(ids.stream()
                                .map(ObjectsIds::getEmployeeId)
                                .toList())
                                .stream().
                                collect(Collectors.toMap(Employee::getId, e -> e)))
                        .build();
            }
            case MEASURING_TOOL -> {
                return new DataBuilder.Data()
                        .organizations(service.getOrganizations(
                                Stream.of(ids.stream()
                                              .map(ObjectsIds::getOrganizationId)
                                                , ids.stream().map(ObjectsIds::getManufacturerId)
                                                , ids.stream().map(ObjectsIds::getToolOwnerId))
                                              .flatMap(Function.identity()).toList())
                                .stream()
                                .collect(Collectors.toMap(Organization::getId, m -> m)))
                        .employees(service.getEmployees(ids.stream()
                                                           .map(ObjectsIds::getEmployeeId)
                                                           .toList())
                                                           .stream()
                                .collect(Collectors.toMap(Employee::getId, e -> e)))
                        .build();
            }
            case LICENSE -> {
                return new DataBuilder.Data()
                        .organizations(service.getOrganizations(Stream.of(ids.stream()
                                                                             .map(ObjectsIds::getOrganizationId)
                                                                        , ids.stream()
                                                                             .map(ObjectsIds::getIssuedLicenseId))
                                                                .flatMap(Function.identity()).toList())
                                                                .stream()
                                                                .collect(Collectors.toMap(Organization::getId, o -> o)))
                        .branches(service.getBranches(ids.stream()
                                                         .map(ObjectsIds::getBranchId)
                                                         .toList()))
                        .departments(service.getDepartments(ids.stream()
                                                               .map(ObjectsIds::getDepartmentId)
                                                               .toList()))
                        .build();
            }
            case APPLICATIONS -> {
                return new DataBuilder.Data()
                        .organizations(service.getOrganizations(ids.stream()
                                .map(ObjectsIds::getOrganizationId).toList())
                                .stream()
                                .collect(Collectors.toMap(Organization::getId, o -> o)))
                        .addresses(service.getAddresses(ids.stream()
                                .map(ObjectsIds::getAddressId)
                                .toList()))
                        .surveyObjects(service.getSurveyObjects(ids.stream()
                                                                .map(ObjectsIds::getSurveyObjectId)
                                                                .toList()))
                        .employees(service.getEmployees(ids.stream()
                                .map(ObjectsIds::getEmployeeId)
                                .toList())
                                .stream().collect(Collectors.toMap(Employee::getId, e -> e)))
                        .reportingDocuments(service.getReportingDocuments(ids.stream()
                                .map(ObjectsIds::getReportingDocumentId)
                                .toList()))
                        .build();
            }
            default -> throw new BadRequestException(String.format("type= %s is not supported", type));
        }
    }
}