package ru.nabokovsg.temlservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.temlservice.models.ColumnHeaderTemplate;

public interface ColumnHeaderTemplateRepository extends JpaRepository<ColumnHeaderTemplate, Long> {
}