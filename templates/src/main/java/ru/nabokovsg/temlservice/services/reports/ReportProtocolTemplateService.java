package ru.nabokovsg.temlservice.services.reports;

import ru.nabokovsg.temlservice.dto.reportProtocol.NewReportProtocolTemplateDto;
import ru.nabokovsg.temlservice.dto.section.SectionTemplateDto;
import ru.nabokovsg.temlservice.models.ReportProtocolTemplate;

public interface ReportProtocolTemplateService {

    SectionTemplateDto save(NewReportProtocolTemplateDto protocolDto);

    ReportProtocolTemplate get(Long id);

    ReportProtocolTemplate saveProtocolTemplate(ReportProtocolTemplate protocol);
}