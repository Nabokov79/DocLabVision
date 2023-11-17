package ru.nabokovsg.dataservice.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.surveyObjectPassport.SurveyObjectPassportDto;
import ru.nabokovsg.dataservice.models.SurveyObjectPassport;

@Mapper(componentModel = "spring")
public interface SurveyObjectPassportMapper {

    SurveyObjectPassportDto mapToPassportDto(SurveyObjectPassport passport);
}