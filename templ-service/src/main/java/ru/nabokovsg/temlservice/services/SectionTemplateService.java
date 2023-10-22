package ru.nabokovsg.temlservice.services;

import ru.nabokovsg.temlservice.dto.section.SectionTemplateDto;
import ru.nabokovsg.temlservice.dto.section.NewSectionTemplateDto;
import ru.nabokovsg.temlservice.dto.template.NewTemplateDataDto;
import ru.nabokovsg.temlservice.models.SubsectionTemplate;

public interface SectionTemplateService {

    SectionTemplateDto save(NewSectionTemplateDto templateDto);

    void addSubsectionTemplate(NewTemplateDataDto template, SubsectionTemplate subsectionTemplate);
}