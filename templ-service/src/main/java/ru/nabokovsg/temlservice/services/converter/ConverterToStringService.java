package ru.nabokovsg.temlservice.services.converter;

import ru.nabokovsg.temlservice.models.TemplateData;

public interface ConverterToStringService {

    String createString(TemplateData data);
}