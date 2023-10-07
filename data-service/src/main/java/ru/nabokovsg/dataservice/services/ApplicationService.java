package ru.nabokovsg.dataservice.services;

import ru.nabokovsg.dataservice.dto.application.*;

import java.util.List;

public interface ApplicationService {

    List<ApplicationDto> save(List<NewApplicationDto> applicationsDto);

    List<ApplicationDto> update(List<UpdateApplicationDto> applicationsDto);

    ApplicationDto get(Long id);

   List<ApplicationDto> getAll(ApplicationSearchParameters parameters);
}