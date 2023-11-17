package ru.nabokovsg.temlservice.services.subsections.reports;

import ru.nabokovsg.temlservice.dto.reportProtocol.ReportProtocolTemplateDto;
import ru.nabokovsg.temlservice.dto.subsection.NewReportProtocolSubsectionTemplateDto;

public interface ReportProtocolSubsectionTemplateService {

    ReportProtocolTemplateDto save(NewReportProtocolSubsectionTemplateDto protocolDto);
}