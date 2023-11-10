package ru.nabokovsg.temlservice.services.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.client.TemplateClient;
import ru.nabokovsg.temlservice.dto.client.*;
import ru.nabokovsg.temlservice.models.SubsectionDataTemplate;
import ru.nabokovsg.temlservice.models.TemplateData;
import ru.nabokovsg.temlservice.models.enums.DataType;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConvertToSubsectionDataServiceImpl implements ConvertToSubsectionDataService {

    private final ConverterToStringService converter;
    private final TemplateClient client;


    @Override
    public List<SubsectionDataTemplate> convert(TemplateData data) {
        switch (data.getType()) {
            case ALL_DOCUMENT ->  {return getAllDocumentation(data.getDocumentations());}
            case REGULATORY_DOCUMENT -> {return getRegulatoryDocumentation(data.getDocumentations());}
            case METHODOLOGICAL_DOCUMENT -> {return getMethodicalDocumentation(data.getDocumentations());}
            case ORGANIZATION -> {return getOrganization(data.getDivisionId());}
            case BRANCH -> {return getBranch(data.getDivisionName(), data.getDivisionId());}
            case DEPARTMENT -> {return getDepartment(data.getDivisionName(), data.getDivisionId());}
            default -> {return new ArrayList<>();}
        }
    }


    private List<SubsectionDataTemplate> getAllDocumentation(List<DocumentationDto> documentations) {
        return documentations.stream()
                .map(d -> {
                    SubsectionDataTemplate template = new SubsectionDataTemplate();
                    template.setSubsectionData(converter.createString(new TemplateData.Builder()
                                                                                      .type(DataType.DOCUMENTATION)
                                                                                      .document(d)
                                                                                      .build()));
                    return template;
                })
                .toList();
    }

    private List<SubsectionDataTemplate> getRegulatoryDocumentation(List<DocumentationDto> documentations) {
        return documentations.stream()
                .filter(d -> d.getRegulatoryDocument().equals(true))
                .map(d -> {
                    SubsectionDataTemplate template = new SubsectionDataTemplate();
                    template.setSubsectionData(converter.createString(new TemplateData.Builder()
                                                                                      .type(DataType.DOCUMENTATION)
                                                                                      .document(d)
                                                                                      .build()));
                    return template;
                })
                .toList();
    }

    private List<SubsectionDataTemplate> getMethodicalDocumentation(List<DocumentationDto> documentations) {
        return documentations.stream()
                .filter(d -> d.getMethodologicalDocument().equals(true))
                .map(d -> {
                    SubsectionDataTemplate template = new SubsectionDataTemplate();
                    template.setSubsectionData(converter.createString(new TemplateData.Builder()
                                                                                      .type(DataType.DOCUMENTATION)
                                                                                      .document(d)
                                                                                      .build()));
                    return template;
                })
                .toList();
    }

    private List<SubsectionDataTemplate> getOrganization(Long divisionId) {
        SubsectionDataTemplate data = new SubsectionDataTemplate();
        data.setSubsectionData(converter.createString(new TemplateData.Builder()
                                                                      .type(DataType.ORGANIZATION)
                                                                      .organization(client.getOrganization(divisionId))
                                                                      .build()));
        return List.of(data);
    }

    private List<SubsectionDataTemplate> getBranch(String divisionName, Long divisionId) {
        SubsectionDataTemplate data = new SubsectionDataTemplate();
        data.setSubsectionData(converter.createString(new TemplateData.Builder()
                                                                      .type(DataType.BRANCH)
                                                                      .divisionName(divisionName)
                                                                      .branch(client.getBranch(divisionId)).build()));
        return List.of(data);
    }

    private List<SubsectionDataTemplate> getDepartment(String divisionName, Long divisionId) {
        SubsectionDataTemplate data = new SubsectionDataTemplate();
        data.setSubsectionData(converter.createString(new TemplateData.Builder()
                                                                      .type(DataType.DEPARTMENT)
                                                                      .divisionName(divisionName)
                                                                      .department(client.getDepartment(divisionId))
                                                                      .build()));
        return List.of(data);
    }
}