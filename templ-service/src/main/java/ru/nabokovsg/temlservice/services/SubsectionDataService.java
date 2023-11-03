package ru.nabokovsg.temlservice.services;

import ru.nabokovsg.temlservice.dto.client.ObjectsTypeDto;
import ru.nabokovsg.temlservice.models.SubsectionDataTemplate;
import ru.nabokovsg.temlservice.models.SubsectionTemplate;

import java.util.List;

public interface SubsectionDataService {

    List<SubsectionDataTemplate> get(SubsectionTemplate template, ObjectsTypeDto objectsType, String divisionType, Long divisionId);
}
