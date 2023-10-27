package ru.nabokovsg.temlservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.temlservice.models.PageHeaderTemplate;

public interface PageHeaderTemplateRepository extends JpaRepository<PageHeaderTemplate, Long> {
}