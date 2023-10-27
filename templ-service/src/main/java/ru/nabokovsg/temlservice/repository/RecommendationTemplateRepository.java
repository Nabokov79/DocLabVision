package ru.nabokovsg.temlservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.temlservice.models.RecommendationTemplate;

public interface RecommendationTemplateRepository extends JpaRepository<RecommendationTemplate, Long> {
}
