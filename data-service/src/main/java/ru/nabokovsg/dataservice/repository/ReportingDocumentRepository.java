package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.dataservice.models.ReportingDocument;

public interface ReportingDocumentRepository extends JpaRepository<ReportingDocument, Long> {
}