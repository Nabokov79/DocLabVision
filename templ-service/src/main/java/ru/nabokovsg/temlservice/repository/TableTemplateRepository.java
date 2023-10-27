package ru.nabokovsg.temlservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.temlservice.models.TableTemplate;

public interface TableTemplateRepository extends JpaRepository<TableTemplate, Long> {
}
