package ru.nabokovsg.dataservice.services;

import ru.nabokovsg.dataservice.dto.surveyObjectSurveys.NewSurveyObjectSurveysDto;
import ru.nabokovsg.dataservice.dto.surveyObjectSurveys.SurveyObjectSurveysDto;
import ru.nabokovsg.dataservice.dto.surveyObjectSurveys.UpdateSurveyObjectSurveysDto;

import java.util.List;

public interface SurveyObjectSurveysService {

    List<SurveyObjectSurveysDto> save(Long surveyObjectId, List<NewSurveyObjectSurveysDto> surveysDto);

    List<SurveyObjectSurveysDto> update(Long surveyObjectId, List<UpdateSurveyObjectSurveysDto> surveysDto);
}