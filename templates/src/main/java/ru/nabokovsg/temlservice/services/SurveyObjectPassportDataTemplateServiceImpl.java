package ru.nabokovsg.temlservice.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.dto.passportData.NewSurveyObjectPassportDataTemplateDto;
import ru.nabokovsg.temlservice.dto.passportData.SurveyObjectPassportDataTemplateDto;
import ru.nabokovsg.temlservice.dto.passportData.UpdateSurveyObjectPassportDataTemplateDto;
import ru.nabokovsg.temlservice.exceptions.NotFoundException;
import ru.nabokovsg.temlservice.mappers.SurveyObjectPassportDataTemplateMapper;
import ru.nabokovsg.temlservice.models.QSurveyObjectPassportDataTemplate;
import ru.nabokovsg.temlservice.models.SurveyObjectPassportDataTemplate;
import ru.nabokovsg.temlservice.repository.SurveyObjectPassportDataTemplateRepository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SurveyObjectPassportDataTemplateServiceImpl implements SurveyObjectPassportDataTemplateService {

    private final SurveyObjectPassportDataTemplateRepository repository;
    private final SurveyObjectPassportDataTemplateMapper mapper;
    private final EntityManager entityManager;

    @Override
    public List<SurveyObjectPassportDataTemplateDto> save(List<Long> objectsTypeId, List<NewSurveyObjectPassportDataTemplateDto> passportDataDto) {
        List<SurveyObjectPassportDataTemplate> templates = mapper.mapToNewSurveyObjectPassportDataTemplate(passportDataDto);
        Map<String, SurveyObjectPassportDataTemplate> templatesDb = getByPredicate(templates
                        .stream()
                        .map(SurveyObjectPassportDataTemplate::getSequentialNumber)
                        .toList()
                , templates.stream()
                        .map(SurveyObjectPassportDataTemplate::getDataName)
                        .toList())
                .stream()
                .collect(Collectors.toMap(SurveyObjectPassportDataTemplate::getDataName, o -> o));
        if (!templatesDb.isEmpty()) {
            templates.addAll(templates.stream()
                    .filter(t -> !templatesDb.containsKey(t.getDataName()))
                    .toList());
        }
        return mapper.mapToSurveyObjectPassportDataTemplateDto(repository.saveAll(templates));
    }

    @Override
    public List<SurveyObjectPassportDataTemplateDto> update(List<UpdateSurveyObjectPassportDataTemplateDto> passportDataDto) {
        validateIds(passportDataDto.stream().map(UpdateSurveyObjectPassportDataTemplateDto::getId).toList());
        return mapper.mapToSurveyObjectPassportDataTemplateDto(
                repository.saveAll(mapper.mapToUpdateSurveyObjectPassportDataTemplate(passportDataDto)));
    }

    private void validateIds(List<Long> ids) {
        Map<Long, SurveyObjectPassportDataTemplate> templates = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(SurveyObjectPassportDataTemplate::getId, m -> m));
        if (templates.size() != ids.size() || templates.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(templates.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("templates with ids= %s not found", ids));
        }
    }

    private List<SurveyObjectPassportDataTemplate> getByPredicate(List<Double> sequentialNumbers
            , List<String> dataNames) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(
                QSurveyObjectPassportDataTemplate.surveyObjectPassportDataTemplate.sequentialNumber.in(sequentialNumbers));
        booleanBuilder.and(
                QSurveyObjectPassportDataTemplate.surveyObjectPassportDataTemplate.dataName.in(dataNames));
        QSurveyObjectPassportDataTemplate template = QSurveyObjectPassportDataTemplate.surveyObjectPassportDataTemplate;
        return new JPAQueryFactory(entityManager).from(template)
                .select(template)
                .where(booleanBuilder)
                .fetch();
    }
}
