package ru.nabokovsg.dataservice.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.ObjectsIds;
import ru.nabokovsg.dataservice.dto.building.BuildingDto;
import ru.nabokovsg.dataservice.dto.building.NewBuildingDto;
import ru.nabokovsg.dataservice.dto.building.UpdateBuildingDto;
import ru.nabokovsg.dataservice.models.Building;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface BuildingMapper {

    Building mapToNewBuilding(NewBuildingDto buildingDto);

    Building mapToUpdateBuilding(UpdateBuildingDto buildingDto);

    List<BuildingDto> mapToBuildingDto(List<Building> buildings);

    List<BuildingDto> mapToBuildingsDto(Set<Building> buildings);

    ObjectsIds mapFromNewBuildingDto(NewBuildingDto buildingDto);

    ObjectsIds mapFromUpdateBuildingDto(UpdateBuildingDto buildingDto);
}