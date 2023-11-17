package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.dataservice.models.SurveyObjectElement;

public interface ObjectsSurveyElementRepository extends JpaRepository<SurveyObjectElement, Long> {
}