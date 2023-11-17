package ru.nabokovsg.temlservice.services.subsections;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.client.TemplateClient;
import ru.nabokovsg.temlservice.dto.client.ObjectsTypeDto;
import ru.nabokovsg.temlservice.dto.subsection.NewSubsectionDataDto;
import ru.nabokovsg.temlservice.dto.subsection.NewSubsectionTemplateDto;
import ru.nabokovsg.temlservice.exceptions.BadRequestException;
import ru.nabokovsg.temlservice.mappers.SubsectionDataTemplateMapper;
import ru.nabokovsg.temlservice.mappers.SubsectionTemplateMapper;
import ru.nabokovsg.temlservice.models.SubsectionDataTemplate;
import ru.nabokovsg.temlservice.models.SubsectionTemplate;
import ru.nabokovsg.temlservice.models.enums.DataType;
import ru.nabokovsg.temlservice.repository.SubsectionDataTemplateRepository;
import ru.nabokovsg.temlservice.services.converter.ConvertToEnumService;
import ru.nabokovsg.temlservice.services.converter.SubsectionDataFactory;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubsectionDataTemplateServiceImpl implements SubsectionDataTemplateService {

    private final SubsectionDataTemplateRepository repository;
    private final SubsectionDataTemplateMapper mapper;
    private final SubsectionTemplateMapper subsectionMapper;
    private final SubsectionDataFactory factory;
    private final ConvertToEnumService convertToEnum;
    private final TemplateClient client;


    @Override
    public List<SubsectionTemplate> getSubsectionDataTemplate(Long objectsTypeId
            , List<NewSubsectionTemplateDto> subsections) {
        ObjectsTypeDto objectType = client.getObjectsType(objectsTypeId);
        List<SubsectionDataTemplate> subsectionData = new ArrayList<>();
                for (NewSubsectionDataDto data : mapper.mapToNewSubsectionDataDto(subsections)) {
                    subsectionData.addAll(getData(objectType, data));
                }
        Map<String, List<SubsectionDataTemplate>> subsectionsData = groupBySubsectionData(save(subsectionData));
        return subsections.stream()
                          .map(s -> {
                                SubsectionTemplate subsection = subsectionMapper.mapToNewSubsectionTemplate(s);
                                List<SubsectionDataTemplate> data = subsectionsData.get(s.getDataType());
                                if (data != null) {
                                    subsection.setSubsectionData(data);
                                }
                                return subsection;
                            })
                          .toList();
    }

    @Override

    public List<SubsectionDataTemplate> save(List<SubsectionDataTemplate> subsectionData) {
        Set<SubsectionDataTemplate> subsectionDataDb = repository.findAllBySubsectionData(
                                                          subsectionData.stream()
                                                                        .map(SubsectionDataTemplate::getSubsectionData)
                                                                        .toList());
        subsectionData = filter(subsectionData, subsectionDataDb.stream()
                                                                .map(SubsectionDataTemplate::getSubsectionData)
                                                                .collect(Collectors.toSet()));
        if (!subsectionData.isEmpty()) {
            if (subsectionDataDb.isEmpty()) {
                return repository.saveAll(subsectionData);
            } else {
                subsectionDataDb.addAll(repository.saveAll(subsectionData));
            }
        }
        return subsectionDataDb.stream().toList();
    }

    private List<SubsectionDataTemplate> filter(List<SubsectionDataTemplate> subsectionsData
                                              , Set<String> subsectionData) {
        return subsectionsData.stream()
                              .filter(s -> !subsectionData.contains(s.getSubsectionData()))
                              .toList();
    }

    private List<SubsectionDataTemplate> getData(ObjectsTypeDto objectType, NewSubsectionDataDto subsectionData) {
        DataType type = convertToEnum.convertToDataType(subsectionData.getDataType());
        switch (type) {
            case NO_DATA -> { return new ArrayList<>(); }
            case ALL_DOCUMENT, REGULATORY_DOCUMENT, METHODOLOGICAL_DOCUMENT ->
            { return factory.createByDocumentationData(type, objectType.getDocumentations())
                                                                         .stream()
                                                                         .peek(s -> s.setDataType(String.valueOf(type)))
                                                                         .toList();
            }
            case LABORATORY_DATA ->
            { return factory.createByDivisionData(
                    convertToEnum.convertToDataType(subsectionData.getDivisionType())
                                                    , subsectionData.getDivisionId()
                                                    , subsectionData.getDivisionName())
                                                                        .stream()
                                                                        .peek(s -> s.setDataType(String.valueOf(type)))
                                                                        .toList();
            }
            default -> throw new BadRequestException(String.format("SubsectionData type=%s is not supported", type));
        }
    }

    private Map<String, List<SubsectionDataTemplate>> groupBySubsectionData(List<SubsectionDataTemplate> subsectionDataDb) {
        Map<String, List<SubsectionDataTemplate>> subsectionData = new HashMap<>();
        for (SubsectionDataTemplate data : subsectionDataDb) {
            if (subsectionData.get(data.getDataType()) == null) {
                subsectionData.put(data.getDataType(), subsectionDataDb.stream()
                                                              .filter(s -> data.getDataType().contains(s.getDataType()))
                                                              .toList());
            }
        }
        return subsectionData;
    }
}