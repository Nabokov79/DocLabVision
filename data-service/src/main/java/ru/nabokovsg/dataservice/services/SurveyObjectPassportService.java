package ru.nabokovsg.dataservice.services;

import ru.nabokovsg.dataservice.dto.surveyObjectPassport.SurveyObjectPassportDto;
import ru.nabokovsg.dataservice.models.SurveyObjectPassport;

public interface SurveyObjectPassportService {

    SurveyObjectPassport save(Long surveyObjectId);

    SurveyObjectPassportDto get(Long id);
}