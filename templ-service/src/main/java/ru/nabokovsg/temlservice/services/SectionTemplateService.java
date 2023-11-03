package ru.nabokovsg.temlservice.services;

import ru.nabokovsg.temlservice.dto.builders.IdsListBuilder;
import ru.nabokovsg.temlservice.dto.report.ReportTemplateDto;
import ru.nabokovsg.temlservice.dto.section.NewReportSectionTemplateDto;
import ru.nabokovsg.temlservice.dto.section.SectionTemplateDto;
import ru.nabokovsg.temlservice.models.SubsectionTemplate;

import java.util.List;

public interface SectionTemplateService {

    ReportTemplateDto save(NewReportSectionTemplateDto sectionTemplateDto);

    SectionTemplateDto addSubsectionTemplate(IdsListBuilder builder, List<SubsectionTemplate> subsectionTemplate);
}