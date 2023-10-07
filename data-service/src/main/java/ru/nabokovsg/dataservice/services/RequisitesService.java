package ru.nabokovsg.dataservice.services;

import ru.nabokovsg.dataservice.dto.requisites.NewRequisitesDto;
import ru.nabokovsg.dataservice.dto.requisites.UpdateRequisitesDto;
import ru.nabokovsg.dataservice.models.Requisites;

public interface RequisitesService {

    Requisites save(NewRequisitesDto requisitesDto);

    Requisites update(UpdateRequisitesDto requisitesDto);
}
