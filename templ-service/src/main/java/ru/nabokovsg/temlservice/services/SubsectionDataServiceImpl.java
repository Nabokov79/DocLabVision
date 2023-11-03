package ru.nabokovsg.temlservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.dto.client.*;
import ru.nabokovsg.temlservice.enums.DivisionType;
import ru.nabokovsg.temlservice.models.SubsectionDataTemplate;
import ru.nabokovsg.temlservice.models.SubsectionTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubsectionDataServiceImpl implements SubsectionDataService {

    private final StringBuilderService stringBuilderService;
    private final ConvertStringToEnumService convert;

    private List<SubsectionDataTemplate> getAllDocumentation(List<DocumentationDto> documentations) {
        return documentations.stream()
                .map(stringBuilderService::documentationToString)
                .map(string -> {
                    SubsectionDataTemplate template = new SubsectionDataTemplate();
                    template.setSubsectionData(string);
                    return template;
                })
                .toList();
    }

    private List<SubsectionDataTemplate> getRegulatoryDocumentation(List<DocumentationDto> documentations) {
        return documentations.stream()
                .filter(o -> o.getRegulatoryDocument().equals(true))
                .map(stringBuilderService::documentationToString)
                .map(string -> {
                    SubsectionDataTemplate template = new SubsectionDataTemplate();
                    template.setSubsectionData(string);
                    return template;
                })
                .toList();
    }

    private List<SubsectionDataTemplate> getMethodicalDocumentation(List<DocumentationDto> documentations) {
        return documentations.stream()
                .filter(o -> o.getMethodologicalDocument().equals(true))
                .map(stringBuilderService::documentationToString)
                .map(string -> {
                    SubsectionDataTemplate template = new SubsectionDataTemplate();
                    template.setSubsectionData(string);
                    return template;
                })
                .toList();
    }

    @Override
    public List<SubsectionDataTemplate> get(SubsectionTemplate template, ObjectsTypeDto objectsType, String divisionType, Long divisionId) {
        switch (template.getSubsectionDataType()) {
            case ALL_DOCUMENT ->  template.setSubsectionData(getAllDocumentation(objectsType.getDocumentations()));
            case REGULATORY_DOCUMENT -> template.setSubsectionData(getRegulatoryDocumentation(objectsType.getDocumentations()));
            case METHODOLOGICAL_DOCUMENT -> template.setSubsectionData(getMethodicalDocumentation(objectsType.getDocumentations()));
            case LABORATORY_DATA_EMPLOYEE_CERTIFICATION -> template.setSubsectionData(List.of(getDivisionData(convert.convertDivisionType(divisionType),divisionId)));
        };
    }

    public SubsectionDataTemplate getDivisionData(DivisionType type, Long divisionId) {
        switch (type) {
            case ORGANIZATION -> {return getOrganizationData(client.getOrganization(divisionId));}
            case BRANCH -> {return getBranchData(client.getBranch(divisionId));}
            case DEPARTMENT -> {return getDepartmentData(client.getDepartment(divisionId));}
        }
    }
}
