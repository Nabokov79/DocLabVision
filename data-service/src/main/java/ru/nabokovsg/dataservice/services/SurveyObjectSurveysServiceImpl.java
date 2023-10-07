package ru.nabokovsg.dataservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.surveyObjectSurveys.NewSurveyObjectSurveysDto;
import ru.nabokovsg.dataservice.dto.surveyObjectSurveys.SurveyObjectSurveysDto;
import ru.nabokovsg.dataservice.dto.surveyObjectSurveys.UpdateSurveyObjectSurveysDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mappers.SurveyObjectSurveysMapper;
import ru.nabokovsg.dataservice.models.SurveyObjectPassport;
import ru.nabokovsg.dataservice.models.SurveyObjectSurveys;
import ru.nabokovsg.dataservice.repository.SurveyObjectSurveysRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SurveyObjectSurveysServiceImpl implements SurveyObjectSurveysService {

    private final SurveyObjectSurveysRepository repository;
    private final SurveyObjectSurveysMapper mapper;
    private final SurveyObjectPassportService passportService;

    @Override
    public List<SurveyObjectSurveysDto> save(Long surveyObjectId, List<NewSurveyObjectSurveysDto> surveysDto) {
        SurveyObjectPassport passport = getSurveyObjectPassport(surveyObjectId);
        return mapper.mapToObjectSurveysDto(
                repository.saveAll(mapper.mapToNewObjectSurveys(surveysDto).stream()
                                                                           .peek(s -> s.setPassport(passport))
                                                                          . toList())
        );
    }

    @Override
    public List<SurveyObjectSurveysDto> update(Long surveyObjectId, List<UpdateSurveyObjectSurveysDto> surveysDto) {
        validateIds(surveysDto.stream().map(UpdateSurveyObjectSurveysDto::getId).toList());
        SurveyObjectPassport passport = getSurveyObjectPassport(surveyObjectId);
        return mapper.mapToObjectSurveysDto(
                repository.saveAll(mapper.mapToUpdateObjectSurveys(surveysDto).stream()
                        .peek(s -> s.setPassport(passport))
                        . toList())
        );
    }

    private void validateIds(List<Long> ids) {
        Map<Long, SurveyObjectSurveys> surveys = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(SurveyObjectSurveys::getId, m -> m));
        if (surveys.size() != ids.size() || surveys.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(surveys.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("surveys with ids= %s not found", ids));
        }
    }

    private SurveyObjectPassport getSurveyObjectPassport(Long surveyObjectId) {
        return passportService.save(surveyObjectId);
    }
}