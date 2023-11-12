package ru.nabokovsg.temlservice.services;

import ru.nabokovsg.temlservice.dto.client.ObjectsTypeDto;
import ru.nabokovsg.temlservice.dto.subsection.NewSubsectionDataDto;
import ru.nabokovsg.temlservice.models.SubsectionDataTemplate;

import java.util.List;

public interface SubsectionDataTemplateService {

    List<SubsectionDataTemplate> save(ObjectsTypeDto objectType, NewSubsectionDataDto subsectionData);
}