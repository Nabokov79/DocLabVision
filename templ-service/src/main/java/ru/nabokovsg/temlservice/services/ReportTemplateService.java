package ru.nabokovsg.temlservice.services;

import ru.nabokovsg.temlservice.dto.report.ReportTemplateDto;
import ru.nabokovsg.temlservice.dto.template.NewTemplateDataDto;

public interface ReportTemplateService {

    ReportTemplateDto save(NewTemplateDataDto templateDto);
}