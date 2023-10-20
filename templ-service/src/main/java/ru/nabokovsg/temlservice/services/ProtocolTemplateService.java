package ru.nabokovsg.temlservice.services;

import ru.nabokovsg.temlservice.dto.templates.DocumentTemplateDto;
import ru.nabokovsg.temlservice.dto.templates.NewTemplateDto;

public interface ProtocolTemplateService {

    DocumentTemplateDto save(NewTemplateDto reportTemplateDto);
}