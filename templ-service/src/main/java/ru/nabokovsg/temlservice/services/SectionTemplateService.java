package ru.nabokovsg.temlservice.services;

import ru.nabokovsg.temlservice.dto.report.ReportTemplateDto;
import ru.nabokovsg.temlservice.dto.section.NewReportSectionTemplateDto;
import ru.nabokovsg.temlservice.models.SectionTemplate;

import java.util.List;

public interface SectionTemplateService {

    ReportTemplateDto save(NewReportSectionTemplateDto sectionTemplateDto);

    List<SectionTemplate> saveAll(List<SectionTemplate> sections);

    SectionTemplate updateSectionTemplate(SectionTemplate section);
}