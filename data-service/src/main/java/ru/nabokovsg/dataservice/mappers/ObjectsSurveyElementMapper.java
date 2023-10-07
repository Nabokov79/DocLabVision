package ru.nabokovsg.dataservice.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.objectsSurveyElement.NewObjectsSurveyElementDto;
import ru.nabokovsg.dataservice.dto.objectsSurveyElement.ObjectsSurveyElementDto;
import ru.nabokovsg.dataservice.dto.objectsSurveyElement.UpdateObjectsSurveyElementDto;
import ru.nabokovsg.dataservice.models.SurveyObjectElement;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ObjectsSurveyElementMapper {

    SurveyObjectElement mapFromNewObjectsSurveyElement(NewObjectsSurveyElementDto elementDto);

    SurveyObjectElement mapFromUpdateObjectsSurveyElement(UpdateObjectsSurveyElementDto elementDto);

    List<ObjectsSurveyElementDto> mapFromObjectsSurveyElementDto(List<SurveyObjectElement> elements);
}