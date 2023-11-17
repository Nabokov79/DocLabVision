package ru.nabokovsg.temlservice.services.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.client.TemplateClient;
import ru.nabokovsg.temlservice.dto.client.*;
import ru.nabokovsg.temlservice.exceptions.BadRequestException;
import ru.nabokovsg.temlservice.models.SubsectionDataTemplate;
import ru.nabokovsg.temlservice.models.TemplateData;
import ru.nabokovsg.temlservice.models.enums.DataType;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubsectionDataFactoryImpl implements SubsectionDataFactory {

    private final StringFactory factory;
    private final TemplateClient client;

    @Override
    public List<SubsectionDataTemplate> createByDocumentationData(DataType type
                                                                , List<DocumentationDto> documentations) {
        switch (type) {
            case ALL_DOCUMENT -> { return convertDocumentationData(documentations);
            }
            case REGULATORY_DOCUMENT -> {
                return convertDocumentationData(documentations.stream()
                                                              .filter(d -> d.getRegulatoryDocument().equals(true))
                                                              .toList());
            }
            case METHODOLOGICAL_DOCUMENT -> {
                return convertDocumentationData(documentations.stream()
                                                              .filter(d -> d.getMethodologicalDocument().equals(true))
                                                              .toList());
            }
            default -> throw new BadRequestException(String.format("data type=%s is not supported", type));
        }
    }

    @Override
    public List<SubsectionDataTemplate> createByDivisionData(DataType divisionType
                                                           , Long divisionId
                                                           , String divisionName) {
        switch (divisionType) {
            case ORGANIZATION ->  { return convertOrganizationData(client.getOrganization(divisionId));
            }
            case BRANCH -> {  return convertBranchData(divisionName, client.getBranch(divisionId));
            }
            case DEPARTMENT -> { return convertDepartmentData(divisionName, client.getDepartment(divisionId));
            }
            default -> throw new BadRequestException(String.format("data type=%s is not supported", divisionType));
        }
    }

    private List<SubsectionDataTemplate> convertDocumentationData(List<DocumentationDto> documentations) {
        return documentations.stream()
                .map(d -> {
                    SubsectionDataTemplate template = new SubsectionDataTemplate();
                    template.setSubsectionData(factory.create(new TemplateData.Builder()
                                                                              .type(DataType.DOCUMENTATION)
                                                                              .document(d)
                                                                              .build()));
                    return template;
                })
                .toList();
    }

    private List<SubsectionDataTemplate> convertOrganizationData(OrganizationDto organization) {
        SubsectionDataTemplate data = new SubsectionDataTemplate();
        data.setSubsectionData(factory.create(new TemplateData.Builder()
                .type(DataType.ORGANIZATION)
                .organization(organization)
                .build()));
        return List.of(data);
    }

    private List<SubsectionDataTemplate> convertBranchData(String divisionName, BranchDto branch) {
        SubsectionDataTemplate data = new SubsectionDataTemplate();
        data.setSubsectionData(factory.create(new TemplateData.Builder()
                .type(DataType.BRANCH)
                .divisionName(divisionName)
                .branch(branch)
                .build()));
        return List.of(data);
    }

    private List<SubsectionDataTemplate> convertDepartmentData(String divisionName, DepartmentDto department) {
        SubsectionDataTemplate data = new SubsectionDataTemplate();
        data.setSubsectionData(factory.create(new TemplateData.Builder()
                .type(DataType.DEPARTMENT)
                .divisionName(divisionName)
                .department(department)
                .build()));
        return List.of(data);
    }
}