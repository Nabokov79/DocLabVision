package ru.nabokovsg.temlservice.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.dto.client.ObjectsTypeDto;
import ru.nabokovsg.temlservice.dto.subsection.NewSubsectionDataDto;
import ru.nabokovsg.temlservice.exceptions.BadRequestException;
import ru.nabokovsg.temlservice.models.SubsectionDataTemplate;
import ru.nabokovsg.temlservice.models.enums.DataType;
import ru.nabokovsg.temlservice.repository.SubsectionDataTemplateRepository;
import ru.nabokovsg.temlservice.services.converter.ConvertToEnumService;
import ru.nabokovsg.temlservice.services.converter.SubsectionDataFactory;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubsectionDataTemplateServiceImpl implements SubsectionDataTemplateService {

    private final SubsectionDataTemplateRepository repository;
    private final SubsectionDataFactory factory;
    private final ConvertToEnumService convertToEnum;

    @Override
    public List<SubsectionDataTemplate> save(ObjectsTypeDto objectType, NewSubsectionDataDto subsectionData) {
        DataType type = convertToEnum.convertToDataType(subsectionData.getDataType());
        switch (type) {
            case NO_DATA -> { return new ArrayList<>(); }
            case ALL_DOCUMENT, REGULATORY_DOCUMENT, METHODOLOGICAL_DOCUMENT ->
            { return repository.saveAll(factory.createByDocumentationData(type, objectType.getDocumentations()));}
            case LABORATORY_DATA ->
            { return repository.saveAll(factory.createByDivisionData(
                                            convertToEnum.convertToDataType(subsectionData.getDivisionType())
                                            , subsectionData.getDivisionId()
                                            , subsectionData.getDivisionName()));}
            default -> throw new BadRequestException(String.format("SubsectionData type=%s is not supported", type));
        }
    }
}