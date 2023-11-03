package ru.nabokovsg.temlservice.services;

import ru.nabokovsg.temlservice.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.temlservice.dto.section.SectionTemplateDto;
import ru.nabokovsg.temlservice.dto.subsection.NewSectionSubsectionTemplateDto;

public interface SubsectionTemplateService {

    SectionTemplateDto saveFromReportTemplate(NewSectionSubsectionTemplateDto templateDto);

    ProtocolTemplateDto saveFromProtocolTemplate(NewSectionSubsectionTemplateDto templateDto);

}