package ru.nabokovsg.dataservice.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.objectPassportDataTemplate.NewObjectPassportDataTemplateDto;
import ru.nabokovsg.dataservice.dto.objectPassportDataTemplate.ObjectPassportDataTemplateDto;
import ru.nabokovsg.dataservice.dto.objectPassportDataTemplate.UpdateObjectPassportDataTemplateDto;
import ru.nabokovsg.dataservice.dto.objectsTypeData.ObjectsTypeTemplateDataDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mappers.ObjectPassportDataTemplateMapper;
import ru.nabokovsg.dataservice.mappers.ObjectsTypeDataMapper;
import ru.nabokovsg.dataservice.models.BuilderType;
import ru.nabokovsg.dataservice.models.DataBuilder;
import ru.nabokovsg.dataservice.models.ObjectPassportDataTemplate;
import ru.nabokovsg.dataservice.models.QObjectPassportDataTemplate;
import ru.nabokovsg.dataservice.repository.ObjectPassportDataTemplateRepository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ObjectPassportDataTemplateServiceImpl implements ObjectPassportDataTemplateService {

    private final ObjectPassportDataTemplateRepository repository;
    private final ObjectPassportDataTemplateMapper mapper;
    private final EntityManager entityManager;
    private final ObjectsTypeDataService dataService;
    private final ObjectsTypeDataMapper dataMapper;

    @Override
    public List<ObjectsTypeTemplateDataDto> save(List<Long> objectsTypeId
                                                                , List<NewObjectPassportDataTemplateDto> templatesDto) {
        List<ObjectPassportDataTemplate> templates = convertStringToInteger(mapper.mapToNewObjectPassportDataTemplate(templatesDto));
        Map<String, ObjectPassportDataTemplate> templatesDb = getByPredicate(templates
                                                        .stream()
                                                        .map(ObjectPassportDataTemplate::getSequentialSubsectionNumber)
                                                        .toList()
                                             , templates.stream()
                                                        .map(ObjectPassportDataTemplate::getDataName)
                                                        .toList())
                .stream().collect(Collectors.toMap(ObjectPassportDataTemplate::getDataName, o -> o));
        if (templatesDb.isEmpty()) {
            return dataMapper.mapToObjectsTypeTemplateDataDto(
                    dataService.save(new DataBuilder.Data().type(BuilderType.TEMPLATE)
                            .ids(objectsTypeId)
                            .templates(repository.saveAll(templates))
                            .build()));
        } else {
            templates = repository.saveAll(templates.stream()
                                                    .filter(t -> !templatesDb.containsKey(t.getDataName()))
                                                    .toList());
            templates.addAll(templatesDb.values());
            return dataMapper.mapToObjectsTypeTemplateDataDto(
                    dataService.save(new DataBuilder.Data().type(BuilderType.TEMPLATE)
                            .ids(objectsTypeId)
                            .templates(templates)
                            .build()));
        }
    }

    @Override
    public List<ObjectPassportDataTemplateDto> update(List<UpdateObjectPassportDataTemplateDto> templatesDto) {
        validateIds(templatesDto.stream().map(UpdateObjectPassportDataTemplateDto::getId).toList());
        return mapper.mapToObjectPassportDataTemplateDto(
                repository.saveAll(convertStringToInteger(mapper.mapToUpdateObjectPassportDataTemplate(templatesDto))));
    }

    private void validateIds(List<Long> ids) {
        Map<Long, ObjectPassportDataTemplate> templates = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(ObjectPassportDataTemplate::getId, m -> m));
        if (templates.size() != ids.size() || templates.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(templates.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("templates with ids= %s not found", ids));
        }
    }

    private List<ObjectPassportDataTemplate> convertStringToInteger(List<ObjectPassportDataTemplate> templates) {
        for (ObjectPassportDataTemplate template : templates) {
            String[] numbers = template.getSequentialSubsectionNumber().split("\\.");
            template.setSectionNumber(Integer.valueOf(numbers[0]));
            template.setSubsectionNumber(Integer.valueOf(numbers[1]));
        }
        return templates;
    }

    private List<ObjectPassportDataTemplate> getByPredicate(List<String> sequentialSubsectionNumbers
                                                                                            , List<String> dataNames) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(
                QObjectPassportDataTemplate.objectPassportDataTemplate
                                           .sequentialSubsectionNumber.in(sequentialSubsectionNumbers));
        booleanBuilder.and(
                QObjectPassportDataTemplate.objectPassportDataTemplate.dataName.in(dataNames));
        QObjectPassportDataTemplate template = QObjectPassportDataTemplate.objectPassportDataTemplate;
        return new JPAQueryFactory(entityManager).from(template)
                                                 .select(template)
                                                 .where(booleanBuilder)
                                                 .fetch();
    }
}