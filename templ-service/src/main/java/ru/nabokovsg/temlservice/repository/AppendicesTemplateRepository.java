package ru.nabokovsg.temlservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.temlservice.models.AppendicesTemplates;

public interface AppendicesTemplateRepository extends JpaRepository<AppendicesTemplates, Long> {
}
