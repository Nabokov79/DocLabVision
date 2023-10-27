package ru.nabokovsg.temlservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.temlservice.models.ConclusionTemplate;

public interface ConclusionTemplateRepository extends JpaRepository<ConclusionTemplate, Long> {
}
