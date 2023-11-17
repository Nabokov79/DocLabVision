package ru.nabokovsg.temlservice.services.converter;

import ru.nabokovsg.temlservice.dto.client.DocumentationDto;
import ru.nabokovsg.temlservice.models.SubsectionDataTemplate;
import ru.nabokovsg.temlservice.models.enums.DataType;

import java.util.List;

public interface SubsectionDataFactory {

    List<SubsectionDataTemplate> createByDocumentationData(DataType type, List<DocumentationDto> documentations);

    List<SubsectionDataTemplate> createByDivisionData(DataType divisionType, Long divisionId, String divisionName);
}