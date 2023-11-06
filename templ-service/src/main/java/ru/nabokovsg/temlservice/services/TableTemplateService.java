package ru.nabokovsg.temlservice.services;

import ru.nabokovsg.temlservice.dto.report.ReportTemplateDto;
import ru.nabokovsg.temlservice.dto.tableTemlate.NewTableTemplateDto;

public interface TableTemplateService {

    ReportTemplateDto save(NewTableTemplateDto templateDto);
}