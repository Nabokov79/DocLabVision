package ru.nabokovsg.temlservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.temlservice.models.SubsectionTemplate;

import java.util.Set;

public interface SubsectionTemplateRepository extends JpaRepository<SubsectionTemplate, Long> {

    Set<SubsectionTemplate> findAllByObjectsTypeIdAndReportingDocumentId(Long objectsTypeId, Long reportingDocumentId);
}
