package ru.nabokovsg.temlservice.services;

import ru.nabokovsg.temlservice.dto.subsection.NewSubsectionTemplateDto;
import ru.nabokovsg.temlservice.dto.subsection.SubsectionTemplateDto;

public interface SubsectionTemplateService {

    SubsectionTemplateDto save(NewSubsectionTemplateDto templateDto);
}