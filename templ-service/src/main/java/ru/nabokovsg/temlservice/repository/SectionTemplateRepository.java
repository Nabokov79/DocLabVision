package ru.nabokovsg.temlservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.temlservice.models.SectionTemplate;

public interface SectionTemplateRepository extends JpaRepository<SectionTemplate, Long> {
}
