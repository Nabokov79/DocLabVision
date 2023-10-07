package ru.nabokovsg.dataservice.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.surveyObjectRepair.NewSurveyObjectRepairDto;
import ru.nabokovsg.dataservice.dto.surveyObjectRepair.SurveyObjectRepairDto;
import ru.nabokovsg.dataservice.dto.surveyObjectRepair.UpdateSurveyObjectRepairDto;
import ru.nabokovsg.dataservice.models.SurveyObjectRepair;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SurveyObjectRepairMapper {

    List<SurveyObjectRepair> mapFromNewRepair(List<NewSurveyObjectRepairDto> repairsDto);

    List<SurveyObjectRepair> mapFromUpdateRepair(List<UpdateSurveyObjectRepairDto> repairsDto);

    List<SurveyObjectRepairDto> mapToRepairDto(List<SurveyObjectRepair> repairs);
}