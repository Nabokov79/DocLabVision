package ru.nabokovsg.temlservice.services;

import ru.nabokovsg.temlservice.models.PassportDataTemplate;

import java.util.List;

public interface PassportDataTemplateService {

    List<PassportDataTemplate> save(Long objectsTypeId, List<PassportDataTemplate> passportData);
}
