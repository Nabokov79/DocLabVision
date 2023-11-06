package ru.nabokovsg.temlservice.services.report;

import ru.nabokovsg.temlservice.builders.TemplateData;
import ru.nabokovsg.temlservice.dto.report.ReportTemplateDto;

public interface ReportTemplateDataService {

    ReportTemplateDto add(TemplateData data);
}