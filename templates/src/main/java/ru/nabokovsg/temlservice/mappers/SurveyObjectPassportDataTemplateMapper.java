package ru.nabokovsg.temlservice.mappers;

import ru.nabokovsg.temlservice.dto.passportData.NewSurveyObjectPassportDataTemplateDto;
import ru.nabokovsg.temlservice.dto.passportData.SurveyObjectPassportDataTemplateDto;
import ru.nabokovsg.temlservice.dto.passportData.UpdateSurveyObjectPassportDataTemplateDto;
import ru.nabokovsg.temlservice.models.SurveyObjectPassportDataTemplate;

import java.util.List;

public interface SurveyObjectPassportDataTemplateMapper {

    List<SurveyObjectPassportDataTemplate> mapToNewSurveyObjectPassportDataTemplate(List<NewSurveyObjectPassportDataTemplateDto> passportDataDto);

    List<SurveyObjectPassportDataTemplate> mapToUpdateSurveyObjectPassportDataTemplate(List<UpdateSurveyObjectPassportDataTemplateDto> passportDataDto);

    List<SurveyObjectPassportDataTemplateDto> mapToSurveyObjectPassportDataTemplateDto(List<SurveyObjectPassportDataTemplate> passportDataDto);
}
