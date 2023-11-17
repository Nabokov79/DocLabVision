package ru.nabokovsg.dataservice.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.ObjectsIds;
import ru.nabokovsg.dataservice.dto.surveyObject.NewSurveyObjectDto;
import ru.nabokovsg.dataservice.dto.surveyObject.SurveyObjectDto;
import ru.nabokovsg.dataservice.dto.surveyObject.ShortSurveyObjectDto;
import ru.nabokovsg.dataservice.dto.surveyObject.UpdateSurveyObjectDto;
import ru.nabokovsg.dataservice.models.SurveyObject;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ObjectSurveyMapper {

    SurveyObject mapToNewObjectSurvey(NewSurveyObjectDto objectDto);

    SurveyObject mapToUpdateObjectSurvey(UpdateSurveyObjectDto objectDto);

    List<ShortSurveyObjectDto> mapToObjectSurveyDtos(List<SurveyObject> objects);

    SurveyObjectDto mapToObjectSurveyDto(SurveyObject object);

    List<ObjectsIds> mapFromNewObjectSurveyIds(List<NewSurveyObjectDto> objectsDto);

    List<ObjectsIds> mapFromUpdateObjectSurveyIds(List<UpdateSurveyObjectDto> objectsDto);
}