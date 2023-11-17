package ru.nabokovsg.temlservice.services.subsections;

import ru.nabokovsg.temlservice.dto.report.ReportTemplateDto;
import ru.nabokovsg.temlservice.dto.subsection.NewSectionSubsectionTemplate;
import ru.nabokovsg.temlservice.models.SubsectionTemplate;

import java.util.List;

public interface SubsectionTemplateService {

    ReportTemplateDto save(NewSectionSubsectionTemplate  subsectionsDto);

    List<SubsectionTemplate> saveAll(List<SubsectionTemplate> subsections);
}