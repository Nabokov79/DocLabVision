package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.dataservice.models.SurveyObjectRepair;

public interface SurveyObjectRepairRepository extends JpaRepository<SurveyObjectRepair, Long> {
}