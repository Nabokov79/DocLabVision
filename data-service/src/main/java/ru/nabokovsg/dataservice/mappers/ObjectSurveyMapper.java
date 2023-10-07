package ru.nabokovsg.dataservice.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.ObjectsIds;
import ru.nabokovsg.dataservice.dto.objectSurvey.NewObjectSurveyDto;
import ru.nabokovsg.dataservice.dto.objectSurvey.ObjectSurveyDto;
import ru.nabokovsg.dataservice.dto.objectSurvey.ShortObjectSurveyDto;
import ru.nabokovsg.dataservice.dto.objectSurvey.UpdateObjectSurveyDto;
import ru.nabokovsg.dataservice.models.SurveyObject;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ObjectSurveyMapper {

    SurveyObject mapToNewObjectSurvey(NewObjectSurveyDto objectDto);

    SurveyObject mapToUpdateObjectSurvey(UpdateObjectSurveyDto objectDto);

    List<ShortObjectSurveyDto> mapToObjectSurveyDtos(List<SurveyObject> objects);

    ObjectSurveyDto mapToObjectSurveyDto(SurveyObject object);

    List<ObjectsIds> mapFromNewObjectSurveyIds(List<NewObjectSurveyDto> objectsDto);

    List<ObjectsIds> mapFromUpdateObjectSurveyIds(List<UpdateObjectSurveyDto> objectsDto);
}