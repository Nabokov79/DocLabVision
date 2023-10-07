package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.dataservice.models.SurveyObject;
import ru.nabokovsg.dataservice.models.SurveyObjectPassport;

public interface SurveyObjectPassportRepository extends JpaRepository<SurveyObjectPassport, Long> {

    SurveyObjectPassport findByObject(SurveyObject object);
}