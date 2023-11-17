package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.dataservice.models.SurveyObject;

public interface ObjectSurveyRepository extends JpaRepository<SurveyObject, Long> {
}