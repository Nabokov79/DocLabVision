package ru.nabokovsg.dataservice.services.builder;

import ru.nabokovsg.dataservice.models.*;

import java.util.List;
import java.util.Map;

public interface RepositoryRequestService {

    List<Building> getBuildings(List<Long> ids);

    List<ObjectsType> getObjectsTypes(List<Long> ids);

    Map<Long, Department> getDepartments(List<Long> ids);

    Map<Long, Address> getAddresses(List<Long> ids);

    List<Element> getElement(List<Long> ids);

    SurveyObject getSurveyObject(Long id);

    List<Organization> getOrganizations(List<Long> ids);

    List<Employee> getEmployees(List<Long> ids);

    Map<Long, Branch> getBranches(List<Long> ids);

    List<ObjectPassportDataTemplate> getTemplates(List<Long> ids);

    Map<Long, ReportingDocument> getReportingDocuments(List<Long> ids);

    Map<Long, SurveyObject> getSurveyObjects(List<Long> ids);
}