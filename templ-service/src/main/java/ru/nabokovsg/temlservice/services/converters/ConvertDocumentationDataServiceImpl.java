package ru.nabokovsg.temlservice.services.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.builders.TemplateData;
import ru.nabokovsg.temlservice.dto.client.*;
import ru.nabokovsg.temlservice.enums.DataType;
import ru.nabokovsg.temlservice.enums.SubsectionDataType;
import ru.nabokovsg.temlservice.models.SubsectionDataTemplate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConvertDocumentationDataServiceImpl implements ConvertDocumentationDataService {

    private final ConvertObjectDataToStringService converter;


    @Override
    public List<SubsectionDataTemplate> convert(SubsectionDataType subsectionDataType, ObjectsTypeDto objectsType) {
        switch (subsectionDataType) {
            case ALL_DOCUMENT ->  {return getAllDocumentation(objectsType.getDocumentations());}
            case REGULATORY_DOCUMENT -> {return getRegulatoryDocumentation(objectsType.getDocumentations());}
            case METHODOLOGICAL_DOCUMENT -> {return getMethodicalDocumentation(objectsType.getDocumentations());}
            default -> {return new ArrayList<>();}
        }
    }


    private List<SubsectionDataTemplate> getAllDocumentation(List<DocumentationDto> documentations) {
        return documentations.stream()
                .map(d -> {
                    SubsectionDataTemplate template = new SubsectionDataTemplate();
                    template.setSubsectionData(converter.createString(new TemplateData.Builder().dataType(DataType.DOCUMENTATION).documentations(d).build()));
                    return template;
                })
                .toList();
    }

    private List<SubsectionDataTemplate> getRegulatoryDocumentation(List<DocumentationDto> documentations) {
        return documentations.stream()
                .filter(d -> d.getRegulatoryDocument().equals(true))
                .map(d -> {
                    SubsectionDataTemplate template = new SubsectionDataTemplate();
                    template.setSubsectionData(converter.createString(new TemplateData.Builder().dataType(DataType.DOCUMENTATION).documentations(d).build()));
                    return template;
                })
                .toList();
    }

    private List<SubsectionDataTemplate> getMethodicalDocumentation(List<DocumentationDto> documentations) {
        return documentations.stream()
                .filter(d -> d.getMethodologicalDocument().equals(true))
                .map(d -> {
                    SubsectionDataTemplate template = new SubsectionDataTemplate();
                    template.setSubsectionData(converter.createString(new TemplateData.Builder().dataType(DataType.DOCUMENTATION).documentations(d).build()));
                    return template;
                })
                .toList();
    }
}
