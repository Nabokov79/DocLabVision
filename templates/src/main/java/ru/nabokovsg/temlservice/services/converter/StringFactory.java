package ru.nabokovsg.temlservice.services.converter;

import ru.nabokovsg.temlservice.models.TemplateData;

public interface StringFactory {

    String create(TemplateData data);
}