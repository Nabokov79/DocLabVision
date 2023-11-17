package ru.nabokovsg.temlservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.temlservice.models.ReportProtocolTemplate;

public interface ReportProtocolTemplateRepository extends JpaRepository<ReportProtocolTemplate, Long> {
}
