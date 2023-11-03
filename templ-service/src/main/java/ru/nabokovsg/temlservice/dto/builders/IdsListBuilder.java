package ru.nabokovsg.temlservice.dto.builders;

public class IdsListBuilder {

    private final Long objectTypeId;
    private final Long reportingDocumentId;
    private final Long sectionId;
    private final Long organizationId;
    private final Long branchId;
    private final Long departmentId;

    public IdsListBuilder(IdsBuilder idsBuilder) {
        this.objectTypeId = idsBuilder.objectTypeId;
        this.reportingDocumentId = idsBuilder.reportingDocumentId;
        this.sectionId = idsBuilder.sectionId;
        this.organizationId = idsBuilder.organizationId;
        this.branchId = idsBuilder.branchId;
        this.departmentId = idsBuilder.departmentId;
    }

    public Long getObjectTypeId() {
        return objectTypeId;
    }

    public Long getReportingDocumentId() {
        return reportingDocumentId;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public static class IdsBuilder {

        private Long objectTypeId;
        private Long reportingDocumentId;
        private Long sectionId;

        private Long organizationId;
        private Long branchId;
        private Long departmentId;

        public IdsBuilder objectTypeId(Long objectTypeId) {
            this.objectTypeId = objectTypeId;
            return this;
        }

        public IdsBuilder reportingDocumentId(Long reportingDocumentId) {
            this.reportingDocumentId = reportingDocumentId;
            return this;
        }

        public IdsBuilder sectionId(Long sectionId) {
            this.sectionId = sectionId;
            return this;
        }

        public IdsBuilder organizationId(Long organizationId) {
            this.organizationId = organizationId;
            return this;
        }
        public IdsBuilder branchId(Long branchId) {
            this.branchId = branchId;
            return this;
        }
        public IdsBuilder departmentId(Long departmentId) {
            this.departmentId = departmentId;
            return this;
        }

        public IdsListBuilder build() {
            return new IdsListBuilder(this);
        }
    }
}
