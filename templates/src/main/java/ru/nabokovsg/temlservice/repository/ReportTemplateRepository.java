package ru.nabokovsg.temlservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.temlservice.models.ReportTemplate;

public interface ReportTemplateRepository extends JpaRepository<ReportTemplate, Long> {
    ReportTemplate findByObjectsTypeIdAndReportingDocumentId(Long objectsTypeId, Long reportingDocumentId);
}