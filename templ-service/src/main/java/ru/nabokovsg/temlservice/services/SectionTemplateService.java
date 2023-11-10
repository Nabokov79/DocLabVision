package ru.nabokovsg.temlservice.services;

import ru.nabokovsg.temlservice.dto.report.ReportTemplateDto;
import ru.nabokovsg.temlservice.dto.section.NewReportSectionTemplateDto;
import ru.nabokovsg.temlservice.models.SectionTemplate;

public interface SectionTemplateService {

    ReportTemplateDto save(NewReportSectionTemplateDto sectionTemplateDto);

    SectionTemplate saveSection(SectionTemplate section);
}