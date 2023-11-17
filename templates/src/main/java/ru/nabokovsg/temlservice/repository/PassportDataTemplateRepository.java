package ru.nabokovsg.temlservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.temlservice.models.PassportDataTemplate;

public interface PassportDataTemplateRepository extends JpaRepository<PassportDataTemplate, Long> {
}
