package ru.nabokovsg.temlservice.services.passportData;

import ru.nabokovsg.temlservice.dto.passportData.NewReportPassportDataTemplateDto;
import ru.nabokovsg.temlservice.dto.section.SectionTemplateDto;

public interface ReportPassportDataTemplateService {

    SectionTemplateDto save(NewReportPassportDataTemplateDto passportDataDto);
}
