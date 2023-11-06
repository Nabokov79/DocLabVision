package ru.nabokovsg.temlservice.services.subsection;

import ru.nabokovsg.temlservice.dto.report.ReportTemplateDto;
import ru.nabokovsg.temlservice.dto.subsection.NewSubsectionTemplateToReportDto;

public interface SubsectionTemplateToReportService {

    ReportTemplateDto save(NewSubsectionTemplateToReportDto templateDto);
}