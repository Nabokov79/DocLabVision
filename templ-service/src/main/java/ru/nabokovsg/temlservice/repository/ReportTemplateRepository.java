package ru.nabokovsg.temlservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.nabokovsg.temlservice.models.ReportTemplate;

import java.util.List;
import java.util.Set;

public interface ReportTemplateRepository extends JpaRepository<ReportTemplate, Long> {

    boolean existsByObjectsTypeIdAndReportingDocumentId(Long objectsTypeId, Long reportingDocumentId);

    ReportTemplate findByObjectsTypeIdAndReportingDocumentId(Long objectsTypeId, Long reportingDocumentId);

    @Query("select r from ReportTemplate r where r.objectsTypeId in :objectsTypeIds and r.reportingDocumentId in :reportingDocumentIds")
    Set<ReportTemplate> findAllByObjectsTypeIdAndReportingDocumentId(
                                                        @Param("objectsTypeIds") List<Long> objectsTypeIds
                                                      , @Param("reportingDocumentIds") List<Long> reportingDocumentIds);
}