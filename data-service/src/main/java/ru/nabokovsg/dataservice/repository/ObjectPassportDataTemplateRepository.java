package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.dataservice.models.ObjectPassportDataTemplate;

public interface ObjectPassportDataTemplateRepository extends JpaRepository<ObjectPassportDataTemplate, Long> {
}