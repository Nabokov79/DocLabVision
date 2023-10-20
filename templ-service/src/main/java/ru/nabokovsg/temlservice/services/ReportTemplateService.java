package ru.nabokovsg.temlservice.services;

import ru.nabokovsg.temlservice.dto.templates.DocumentTemplateDto;
import ru.nabokovsg.temlservice.dto.templates.NewTemplateDto;

public interface ReportTemplateService {

    DocumentTemplateDto save(NewTemplateDto reportTemplateDto);
}