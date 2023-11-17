package ru.nabokovsg.temlservice.services.subsections;

import ru.nabokovsg.temlservice.dto.subsection.NewSubsectionTemplateDto;
import ru.nabokovsg.temlservice.models.SubsectionDataTemplate;
import ru.nabokovsg.temlservice.models.SubsectionTemplate;

import java.util.List;

public interface SubsectionDataTemplateService {

    List<SubsectionTemplate> getSubsectionDataTemplate(Long objectsTypeId, List<NewSubsectionTemplateDto> subsections);

    List<SubsectionDataTemplate> save(List<SubsectionDataTemplate> subsectionData);
}