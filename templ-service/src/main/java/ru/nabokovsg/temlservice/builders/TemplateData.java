package ru.nabokovsg.temlservice.builders;

import ru.nabokovsg.temlservice.dto.client.*;
import ru.nabokovsg.temlservice.enums.DataType;
import ru.nabokovsg.temlservice.enums.DivisionType;
import ru.nabokovsg.temlservice.models.ReportTemplate;
import ru.nabokovsg.temlservice.models.SectionTemplate;
import ru.nabokovsg.temlservice.models.SubsectionTemplate;
import ru.nabokovsg.temlservice.models.TableTemplate;

import java.util.List;

public class TemplateData {

    private final DivisionType type;
    private final DataType dataType;
    private final List<SubsectionTemplate> subsectionTemplates;
    private final Long objectsTypeId;
    private final Long reportingDocumentId;
    private final Long sectionId;
    private final ObjectsTypeDto objectsType;
    private final List<LicenseDto> licenses;
    private final RequisitesDto requisites;
    private final AddressDto address;
    private final EmployeeDto employee;
    private final DocumentationDto documentations;
    private final OrganizationDto organization;
    private final BranchDto branch;
    private final DepartmentDto department;
    private final String divisionName;
    private final TableTemplate table;
    private final double sequentialSubsectionNumber;
    private final List<SectionTemplate> sectionTemplates;
    private final ReportTemplate report;

    public TemplateData(Builder builder) {
        this.type = builder.type;
        this.objectsTypeId = builder.objectsTypeId;
        this.reportingDocumentId = builder.reportingDocumentId;
        this.subsectionTemplates = builder.subsectionTemplates;
        this.sectionId = builder.sectionId;
        this.objectsType = builder.objectsType;
        this.licenses = builder.licenses;
        this.requisites = builder.requisites;
        this.address = builder.address;
        this.employee = builder.employee;
        this.documentations = builder.documentations;
        this.organization = builder.organization;
        this.branch = builder.branch;
        this.department = builder.department;
        this.divisionName = builder.divisionName;
        this.table = builder.table;
        this.sequentialSubsectionNumber = builder.sequentialSubsectionNumber;
        this.sectionTemplates = builder.sectionTemplates;
        this.report = builder.report;
        this.dataType = builder.dataType;
    }

    public List<SubsectionTemplate> getSubsectionTemplates() {
        return subsectionTemplates;
    }

    public Long getObjectsTypeId() {
        return objectsTypeId;
    }

    public Long getReportingDocumentId() {
        return reportingDocumentId;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public ObjectsTypeDto getObjectsType() {
        return objectsType;
    }

    public List<LicenseDto> getLicenses() {
        return licenses;
    }

    public RequisitesDto getRequisites() {
        return requisites;
    }

    public AddressDto getAddress() {
        return address;
    }

    public EmployeeDto getEmployee() {
        return employee;
    }

    public DocumentationDto getDocumentations() {
        return documentations;
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

    public String getDivisionName() {
        return divisionName;
    }

    public DivisionType getType() {
        return type;
    }

    public TableTemplate getTable() {
        return table;
    }

    public double getSequentialSubsectionNumber() {
        return sequentialSubsectionNumber;
    }

    public List<SectionTemplate> getSectionTemplates() {
        return sectionTemplates;
    }

    public DataType getDataType() {
        return dataType;
    }

    public ReportTemplate getReport() {
        return report;
    }

    public static class Builder {

        private DivisionType type;
        private DataType dataType;
        private List<SubsectionTemplate> subsectionTemplates;
        private Long objectsTypeId;
        private Long reportingDocumentId;
        private Long sectionId;
        private ObjectsTypeDto objectsType;
        private List<LicenseDto> licenses;
        private RequisitesDto requisites;
        private AddressDto address;
        private EmployeeDto employee;
        private DocumentationDto documentations;
        private OrganizationDto organization;
        private BranchDto branch;
        private DepartmentDto department;
        private String divisionName;
        private TableTemplate table;
        private double sequentialSubsectionNumber;
        private List<SectionTemplate> sectionTemplates;
        private ReportTemplate report;

        public Builder type(DivisionType type) {
            this.type = type;
            return this;
        }

        public Builder dataType(DataType dataType) {
            this.dataType = dataType;
            return this;
        }
        public Builder subsectionTemplates(List<SubsectionTemplate> subsectionTemplates) {
            this.subsectionTemplates = subsectionTemplates;
            return this;
        }

        public Builder objectsTypeId(Long objectsTypeId) {
            this.objectsTypeId = objectsTypeId;
            return this;
        }

        public Builder reportingDocumentId(Long reportingDocumentId) {
            this.reportingDocumentId = reportingDocumentId;
            return this;
        }

        public Builder sectionId(Long sectionId) {
            this.sectionId = sectionId;
            return this;
        }

        public Builder objectsType(ObjectsTypeDto objectsType) {
            this.objectsType = objectsType;
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

        public Builder address(AddressDto address) {
            this.address = address;
            return this;
        }

        public Builder employee(EmployeeDto employee) {
            this.employee = employee;
            return this;
        }

        public Builder documentations(DocumentationDto documentations) {
            this.documentations = documentations;
            return this;
        }

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

        public Builder divisionName(String divisionName) {
            this.divisionName = divisionName;
            return this;
        }

        public Builder table(TableTemplate table) {
            this.table = table;
            return this;
        }

        public Builder sectionTemplates(List<SectionTemplate> sectionTemplates) {
            this.sectionTemplates = sectionTemplates;
            return this;
        }

        public Builder sequentialSubsectionNumber(double sequentialSubsectionNumber) {
            this.sequentialSubsectionNumber = sequentialSubsectionNumber;
            return this;
        }

        public Builder report(ReportTemplate report) {
            this.report = report;
            return this;
        }

        public TemplateData build() {
            return new TemplateData(this);
        }
    }
}