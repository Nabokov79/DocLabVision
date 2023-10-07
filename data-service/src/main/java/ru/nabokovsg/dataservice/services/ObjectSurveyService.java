package ru.nabokovsg.dataservice.services;

import ru.nabokovsg.dataservice.dto.objectSurvey.NewObjectSurveyDto;
import ru.nabokovsg.dataservice.dto.objectSurvey.ObjectSurveyDto;
import ru.nabokovsg.dataservice.dto.objectSurvey.ShortObjectSurveyDto;
import ru.nabokovsg.dataservice.dto.objectSurvey.UpdateObjectSurveyDto;

import java.util.List;

public interface ObjectSurveyService {

    List<ShortObjectSurveyDto> save(List<NewObjectSurveyDto> objectsDto);

    List<ShortObjectSurveyDto> update(List<UpdateObjectSurveyDto> objectsDto);

    ObjectSurveyDto get(Long id);

     void delete(Long id);
}