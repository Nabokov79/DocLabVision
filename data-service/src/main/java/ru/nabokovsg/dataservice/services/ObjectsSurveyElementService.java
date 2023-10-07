package ru.nabokovsg.dataservice.services;

import ru.nabokovsg.dataservice.dto.objectsSurveyElement.NewObjectsSurveyElementDto;
import ru.nabokovsg.dataservice.dto.objectsSurveyElement.ObjectsSurveyElementDto;
import ru.nabokovsg.dataservice.dto.objectsSurveyElement.UpdateObjectsSurveyElementDto;

import java.util.List;

public interface ObjectsSurveyElementService {

    List<ObjectsSurveyElementDto> save(Long surveyObjectId, List<NewObjectsSurveyElementDto> elementsDataDto);

    List<ObjectsSurveyElementDto> update(Long surveyObjectId, List<UpdateObjectsSurveyElementDto> elementsDataDto);

    void delete(Long id);
}