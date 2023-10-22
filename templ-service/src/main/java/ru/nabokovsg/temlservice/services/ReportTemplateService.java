package ru.nabokovsg.temlservice.services;

import ru.nabokovsg.temlservice.dto.report.ReportTemplateDto;
import ru.nabokovsg.temlservice.dto.template.NewTemplateDataDto;
import ru.nabokovsg.temlservice.models.PageTitleTemplate;
import ru.nabokovsg.temlservice.models.SectionTemplate;
import ru.nabokovsg.temlservice.models.SubsectionTemplate;

import java.util.List;

public interface ReportTemplateService {

    ReportTemplateDto save(NewTemplateDataDto templateDto);

    void addPageTitleTemplate(NewTemplateDataDto template, PageTitleTemplate pageTitleTemplate);

    void addSectionTemplate(List<Long> objectsTypeIds, List<Long> reportingDocumentIds, SectionTemplate sectionTemplate);
}