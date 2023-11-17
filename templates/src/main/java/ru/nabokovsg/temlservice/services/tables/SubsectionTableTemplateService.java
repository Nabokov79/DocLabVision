package ru.nabokovsg.temlservice.services.tables;

import ru.nabokovsg.temlservice.dto.section.SectionTemplateDto;
import ru.nabokovsg.temlservice.dto.table.NewSubsectionTableTemplateDto;

public interface SubsectionTableTemplateService {

    SectionTemplateDto save(NewSubsectionTableTemplateDto templateDto);
}