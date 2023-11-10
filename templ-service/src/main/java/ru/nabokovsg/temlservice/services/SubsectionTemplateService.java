package ru.nabokovsg.temlservice.services;

import ru.nabokovsg.temlservice.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.temlservice.dto.report.ReportTemplateDto;
import ru.nabokovsg.temlservice.dto.subsection.NewProtocolSubsectionTemplate;
import ru.nabokovsg.temlservice.dto.subsection.NewSectionSubsectionTemplate;

public interface SubsectionTemplateService {

    ReportTemplateDto saveToSectionTemplate(NewSectionSubsectionTemplate sectionsDto);

    ProtocolTemplateDto saveToProtocolTemplate(NewProtocolSubsectionTemplate sectionsDto);
}
