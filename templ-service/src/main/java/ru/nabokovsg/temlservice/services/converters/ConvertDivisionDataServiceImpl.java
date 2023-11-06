package ru.nabokovsg.temlservice.services.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.builders.TemplateData;
import ru.nabokovsg.temlservice.client.TemplateClient;
import ru.nabokovsg.temlservice.dto.division.Division;
import ru.nabokovsg.temlservice.exceptions.BadRequestException;
import ru.nabokovsg.temlservice.models.SubsectionDataTemplate;

@Service
@RequiredArgsConstructor
public class ConvertDivisionDataServiceImpl implements ConvertDivisionDataService {

    private final ConvertObjectDataToStringService converter;
    private final TemplateClient client;

    @Override
    public SubsectionDataTemplate convert(Division division) {
        SubsectionDataTemplate data = new SubsectionDataTemplate();
        switch (division.getDivisionType()) {
            case ORGANIZATION -> data.setSubsectionData(converter.createString(new TemplateData.Builder().type(division.getDivisionType()).organization(client.getOrganization(division.getDivisionId())).build()));
            case BRANCH -> data.setSubsectionData(converter.createString(new TemplateData.Builder().type(division.getDivisionType()).branch(client.getBranch(division.getDivisionId())).divisionName(division.getDivisionName()).build()));
            case DEPARTMENT -> data.setSubsectionData(converter.createString(new TemplateData.Builder().type(division.getDivisionType()).department(client.getDepartment(division.getDivisionId())).divisionName(division.getDivisionName()).build()));
            default -> throw new BadRequestException(String.format("Unknown division type=%s", division.getDivisionType()));
        }
        return data;
    }
}
