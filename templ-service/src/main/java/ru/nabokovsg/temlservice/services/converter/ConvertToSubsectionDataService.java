package ru.nabokovsg.temlservice.services.converter;

import ru.nabokovsg.temlservice.models.SubsectionDataTemplate;
import ru.nabokovsg.temlservice.models.TemplateData;

import java.util.List;

public interface ConvertToSubsectionDataService {

    List<SubsectionDataTemplate> convert(TemplateData data);
}