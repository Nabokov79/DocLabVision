package ru.nabokovsg.temlservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.temlservice.models.PageTitleTemplate;

public interface PageTitleTemplateRepository extends JpaRepository<PageTitleTemplate, Long> {
}