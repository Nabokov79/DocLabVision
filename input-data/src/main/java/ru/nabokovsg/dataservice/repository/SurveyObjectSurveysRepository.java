package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.dataservice.models.SurveyObjectSurveys;

public interface SurveyObjectSurveysRepository extends JpaRepository<SurveyObjectSurveys, Long> {
}