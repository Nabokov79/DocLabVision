package ru.nabokovsg.dataservice.services;

import ru.nabokovsg.dataservice.dto.surveyObject.NewSurveyObjectDto;
import ru.nabokovsg.dataservice.dto.surveyObject.SurveyObjectDto;
import ru.nabokovsg.dataservice.dto.surveyObject.ShortSurveyObjectDto;
import ru.nabokovsg.dataservice.dto.surveyObject.UpdateSurveyObjectDto;

import java.util.List;

public interface ObjectSurveyService {

    List<ShortSurveyObjectDto> save(List<NewSurveyObjectDto> objectsDto);

    List<ShortSurveyObjectDto> update(List<UpdateSurveyObjectDto> objectsDto);

    SurveyObjectDto get(Long id);

     void delete(Long id);
}