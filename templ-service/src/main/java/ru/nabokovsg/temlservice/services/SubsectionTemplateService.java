package ru.nabokovsg.temlservice.services;

import ru.nabokovsg.temlservice.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.temlservice.dto.report.ReportTemplateDto;
import ru.nabokovsg.temlservice.dto.subsection.NewProtocolSubsectionTemplate;
import ru.nabokovsg.temlservice.dto.subsection.NewSectionSubsectionTemplate;
import ru.nabokovsg.temlservice.models.SubsectionTemplate;

public interface SubsectionTemplateService {

    ReportTemplateDto addToSectionTemplate(NewSectionSubsectionTemplate sectionsDto);

    ProtocolTemplateDto addToProtocolTemplate(NewProtocolSubsectionTemplate sectionsDto);

    SubsectionTemplate save(SubsectionTemplate subsections);
}
