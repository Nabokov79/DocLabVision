package ru.nabokovsg.temlservice.services;

import ru.nabokovsg.temlservice.dto.section.SectionTemplateDto;
import ru.nabokovsg.temlservice.dto.section.NewSectionTemplateDto;

public interface SectionTemplateService {

    SectionTemplateDto save(NewSectionTemplateDto templateDto);
}