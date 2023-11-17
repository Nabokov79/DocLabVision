package ru.nabokovsg.temlservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.temlservice.models.HeaderTemplate;

public interface HeaderTemplateRepository extends JpaRepository<HeaderTemplate, Long> {
}