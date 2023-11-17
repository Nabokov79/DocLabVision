package ru.nabokovsg.temlservice.services.tables;

import ru.nabokovsg.temlservice.dto.reportProtocol.ReportProtocolTemplateDto;
import ru.nabokovsg.temlservice.dto.table.NewProtocolTableTemplateDto;

public interface ReportProtocolTableTemplateService {

    ReportProtocolTemplateDto save(NewProtocolTableTemplateDto templateDto);
}