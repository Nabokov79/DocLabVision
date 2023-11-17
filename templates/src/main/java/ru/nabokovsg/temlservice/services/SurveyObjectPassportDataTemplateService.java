package ru.nabokovsg.temlservice.services;

import ru.nabokovsg.temlservice.dto.passportData.NewSurveyObjectPassportDataTemplateDto;
import ru.nabokovsg.temlservice.dto.passportData.SurveyObjectPassportDataTemplateDto;
import ru.nabokovsg.temlservice.dto.passportData.UpdateSurveyObjectPassportDataTemplateDto;

import java.util.List;

public interface SurveyObjectPassportDataTemplateService {

    List<SurveyObjectPassportDataTemplateDto> save(List<Long> objectsTypeId
            , List<NewSurveyObjectPassportDataTemplateDto> passportDataDto);

    List<SurveyObjectPassportDataTemplateDto> update(List<UpdateSurveyObjectPassportDataTemplateDto> passportDataDto);
}
