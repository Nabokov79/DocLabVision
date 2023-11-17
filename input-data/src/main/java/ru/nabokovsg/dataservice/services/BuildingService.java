package ru.nabokovsg.dataservice.services;

import ru.nabokovsg.dataservice.dto.building.BuildingDto;
import ru.nabokovsg.dataservice.dto.building.NewBuildingDto;
import ru.nabokovsg.dataservice.dto.building.UpdateBuildingDto;

import java.util.List;

public interface BuildingService {

    List<BuildingDto> save(List<NewBuildingDto> buildingsDto);

    List<BuildingDto> update(List<UpdateBuildingDto> buildingsDto);

    List<BuildingDto> getAll(Long departmentId);

    void  delete(Long id);
}