package ru.nabokovsg.dataservice.models;

import ru.nabokovsg.dataservice.enums.BuilderType;

import java.util.List;
import java.util.Map;

public class DataBuilder {

    private final List<Long> objectsTypeIds;
    private final BuilderType type;
    private final List<Building> buildings;
    private final List<ObjectsType> objectsTypes;
    private final Map<Long, Department> departments;
    private final Map<Long, Address> addresses;
    private final List<Element> elements;
    private final List<Defect> defects;
    private final List<Norm> norms;
    private final List<Documentation> documentations;
    private final List<ObjectPassportDataTemplate> templates;
    private final List<RepairMethod> methods;
    private final Map<Long, Organization> organizations;
    private final Map<Long, Employee> employees;
    private final Map<Long, Branch> branches;
    private final Map<Long, SurveyObject> surveyObjects;
    private final Map<Long, ReportingDocument> reportingDocuments;

    private DataBuilder(Data data) {
        this.objectsTypeIds = data.objectsTypeIds;
        this.buildings = data.buildings;
        this.objectsTypes = data.objectsTypes;
        this.departments = data.departments;
        this.addresses = data.addresses;
        this.type =  data.type;
        this.elements =  data.elements;
        this.defects =  data.defects;
        this.norms =  data.norms;
        this.documentations =  data.documentations;
        this.templates =  data.templates;
        this.methods =  data.methods;
        this.organizations = data.organizations;
        this.employees = data.employees;
        this.branches = data.branches;
        this.surveyObjects = data.surveyObjects;
        this.reportingDocuments = data.reportingDocuments;
    }

    public List<Long> getObjectsTypeIds() {
        return objectsTypeIds;
    }

    public BuilderType getType() {
        return type;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public List<ObjectsType> getObjectsTypes() {
        return objectsTypes;
    }

    public Map<Long, Department> getDepartments() {
        return departments;
    }

    public Map<Long, Address> getAddresses() {
        return addresses;
    }

    public List<Element> getElements() {
        return elements;
    }

    public List<Defect> getDefects() {
        return defects;
    }

    public List<Norm> getNorms() {
        return norms;
    }

    public List<Documentation> getDocumentations() {
        return documentations;
    }

    public List<ObjectPassportDataTemplate> getTemplates() {
        return templates;
    }

    public List<RepairMethod> getMethods() {
        return methods;
    }

    public Map<Long, Organization> getOrganizations() {
        return organizations;
    }

    public Map<Long, Employee> getEmployees() {
        return employees;
    }

    public Map<Long, Branch> getBranches() {
        return branches;
    }

    public Map<Long, SurveyObject> getSurveyObjects() {
        return surveyObjects;
    }

    public Map<Long,ReportingDocument> getReportingDocuments() {
        return reportingDocuments;
    }

    public static class Data {

        private List<Long> objectsTypeIds;
        private List<Building> buildings;
        private List<ObjectsType> objectsTypes;
        private Map<Long, Department> departments;
        private Map<Long, Address> addresses;
        private BuilderType type;
        private List<Element> elements;
        private List<Defect> defects;
        private List<Norm> norms;
        private List<Documentation> documentations;
        private List<ObjectPassportDataTemplate> templates;
        private List<RepairMethod> methods;
        private Map<Long, Organization> organizations;
        private Map<Long, Employee> employees;
        private Map<Long, Branch> branches;
        private Map<Long, SurveyObject> surveyObjects;
        private Map<Long,ReportingDocument> reportingDocuments;

        public Data ids(List<Long> objectsTypeIds) {
            this.objectsTypeIds = objectsTypeIds;
            return this;
        }

        public Data buildings(List<Building> buildings) {
            this.buildings = buildings;
            return this;
        }

        public Data objectsTypes(List<ObjectsType> objectsTypes) {
            this.objectsTypes = objectsTypes;
            return this;
        }

        public Data departments(Map<Long, Department> departments) {
            this.departments = departments;
            return this;
        }

        public Data addresses(Map<Long, Address> addresses) {
            this.addresses = addresses;
            return this;
        }

        public Data elements(List<Element> elements) {
            this.elements = elements;
            return this;
        }

        public Data type(BuilderType type) {
            this.type = type;
            return this;
        }

        public Data defects(List<Defect> defects) {
            this.defects = defects;
            return this;
        }

        public Data norms(List<Norm> norms) {
            this.norms = norms;
            return this;
        }

        public Data documentations(List<Documentation> documentations) {
            this.documentations = documentations;
            return this;
        }

        public Data templates(List<ObjectPassportDataTemplate> templates) {
            this.templates = templates;
            return this;
        }

        public Data methods(List<RepairMethod> methods) {
            this.methods = methods;
            return this;
        }

        public Data organizations(Map<Long, Organization> organizations) {
            this.organizations = organizations;
            return this;
        }

        public Data employees(Map<Long, Employee> employees) {
            this.employees = employees;
            return this;
        }

        public Data branches(Map<Long, Branch> branches) {
            this.branches = branches;
            return this;
        }

        public Data surveyObjects( Map<Long, SurveyObject> surveyObjects) {
            this.surveyObjects = surveyObjects;
            return this;
        }

        public Data reportingDocuments(Map<Long, ReportingDocument> reportingDocuments) {
            this.reportingDocuments = reportingDocuments;
            return this;
        }

        public DataBuilder build() {
            return new DataBuilder(this);
        }
    }
}