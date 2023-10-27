package ru.nabokovsg.temlservice.services;

import ru.nabokovsg.temlservice.dto.subsection.NewSectionSubsectionTemplateDto;
import ru.nabokovsg.temlservice.dto.subsection.NewSubsectionTemplateDto;
import ru.nabokovsg.temlservice.dto.subsection.SubsectionTemplateDto;

import java.util.List;

public interface SubsectionTemplateService {

    List<SubsectionTemplateDto> save(NewSectionSubsectionTemplateDto templateDto);
}