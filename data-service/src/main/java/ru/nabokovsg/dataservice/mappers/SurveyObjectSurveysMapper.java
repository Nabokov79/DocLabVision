package ru.nabokovsg.dataservice.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.surveyObjectSurveys.NewSurveyObjectSurveysDto;
import ru.nabokovsg.dataservice.dto.surveyObjectSurveys.SurveyObjectSurveysDto;
import ru.nabokovsg.dataservice.dto.surveyObjectSurveys.UpdateSurveyObjectSurveysDto;
import ru.nabokovsg.dataservice.models.SurveyObjectSurveys;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SurveyObjectSurveysMapper {

    List<SurveyObjectSurveys> mapToNewObjectSurveys(List<NewSurveyObjectSurveysDto> surveysDto);

    List<SurveyObjectSurveys> mapToUpdateObjectSurveys(List<UpdateSurveyObjectSurveysDto> surveysDto);

    List<SurveyObjectSurveysDto> mapToObjectSurveysDto(List<SurveyObjectSurveys> surveys);
}