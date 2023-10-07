package ru.nabokovsg.dataservice.services;

import ru.nabokovsg.dataservice.dto.surveyObjectRepair.NewSurveyObjectRepairDto;
import ru.nabokovsg.dataservice.dto.surveyObjectRepair.SurveyObjectRepairDto;
import ru.nabokovsg.dataservice.dto.surveyObjectRepair.UpdateSurveyObjectRepairDto;

import java.util.List;

public interface SurveyObjectRepairService {

    List<SurveyObjectRepairDto> save(Long surveyObjectId, List<NewSurveyObjectRepairDto> repairsDto);

    List<SurveyObjectRepairDto> update(Long surveyObjectId, List<UpdateSurveyObjectRepairDto> repairsDto);
}