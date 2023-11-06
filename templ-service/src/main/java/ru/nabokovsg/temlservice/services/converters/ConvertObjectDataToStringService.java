package ru.nabokovsg.temlservice.services.converters;

import ru.nabokovsg.temlservice.builders.TemplateData;

public interface ConvertObjectDataToStringService {

    String createString(TemplateData data);
}