package ru.nabokovsg.temlservice.models;

import ru.nabokovsg.temlservice.dto.client.*;
import ru.nabokovsg.temlservice.models.enums.DataType;

import java.util.List;

public class TemplateData {

    private final DataType type;
    private final List<LicenseDto> licenses;
    private final RequisitesDto requisites;
    private final EmployeeDto employee;
    private final Long divisionId;
    private final List<DocumentationDto> documentations;
    private final DocumentationDto document;
    private final String divisionName;
    private final OrganizationDto organization;
    private final BranchDto branch;
    private final DepartmentDto department;

    public TemplateData(Builder builder) {
        this.type = builder.type;
        this.licenses = builder.licenses;
        this.requisites = builder.requisites;
        this.employee = builder.employee;
        this.divisionId = builder.divisionId;
        this.documentations = builder.documentations;
        this.document = builder.document;
        this.divisionName = builder.divisionName;
        this.organization = builder.organization;
        this.branch = builder.branch;
        this.department = builder.department;
    }

    public DataType getType() {
        return type;
    }

    public List<LicenseDto> getLicenses() {
        return licenses;
    }

    public RequisitesDto getRequisites() {
        return requisites;
    }

    public EmployeeDto getEmployee() {
        return employee;
    }

    public Long getDivisionId() {
        return divisionId;
    }

    public List <DocumentationDto> getDocumentations() {
        return documentations;
    }

    public DocumentationDto getDocument() {
        return document;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public OrganizationDto getOrganization() {
        return organization;
    }

    public BranchDto getBranch() {
        return branch;
    }

    public DepartmentDto getDepartment() {
        return department;
    }

    public static class Builder {

        private DataType type;
        private List<LicenseDto> licenses;
        private RequisitesDto requisites;
        private EmployeeDto employee;
        private Long divisionId;
        private List<DocumentationDto> documentations;
        private DocumentationDto document;
        private String divisionName;
        private OrganizationDto organization;
        private BranchDto branch;
        private DepartmentDto department;

        public Builder organization(OrganizationDto organization) {
            this.organization = organization;
            return this;
        }

        public Builder branch(BranchDto branch) {
            this.branch = branch;
            return this;
        }

        public Builder department(DepartmentDto department) {
            this.department = department;
            return this;
        }
        public Builder type(DataType type) {
            this.type = type;
            return this;
        }

        public Builder licenses(List<LicenseDto> licenses) {
            this.licenses = licenses;
            return this;
        }

        public Builder requisites(RequisitesDto requisites) {
            this.requisites = requisites;
            return this;
        }

        public Builder employee(EmployeeDto employee) {
            this.employee = employee;
            return this;
        }

        public Builder divisionId(Long divisionId) {
            this.divisionId = divisionId;
            return this;
        }

        public Builder documentations(List<DocumentationDto> documentations) {
            this.documentations = documentations;
            return this;
        }

        public Builder document(DocumentationDto document) {
            this.document = document;
            return this;
        }

        public Builder divisionName(String divisionName) {
            this.divisionName = divisionName;
            return this;
        }

        public TemplateData build() {
            return new TemplateData(this);
        }
    }
}