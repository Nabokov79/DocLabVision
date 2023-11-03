package ru.nabokovsg.temlservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.temlservice.models.ProtocolTemplate;

import java.util.Set;

public interface ProtocolTemplateRepository extends JpaRepository<ProtocolTemplate, Long> {

   boolean existsByObjectsTypeIdAndReportingDocumentId(Long objectsTypeId, Long reportingDocumentId);

   ProtocolTemplate findByObjectsTypeIdAndReportingDocumentId(Long objectsTypeId, Long reportingDocumentId);
}