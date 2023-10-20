package ru.nabokovsg.temlservice.services;

import ru.nabokovsg.temlservice.dto.templates.DocumentTemplateDto;
import ru.nabokovsg.temlservice.dto.templates.NewTemplateDto;

public interface TemplateService {

    DocumentTemplateDto save(NewTemplateDto reportTemplateDto);
}