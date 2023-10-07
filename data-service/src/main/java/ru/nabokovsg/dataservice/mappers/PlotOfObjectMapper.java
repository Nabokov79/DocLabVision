package ru.nabokovsg.dataservice.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.plotOfObject.NewPlotOfObjectDto;
import ru.nabokovsg.dataservice.dto.plotOfObject.UpdatePlotOfObjectDto;
import ru.nabokovsg.dataservice.models.PlotOfObject;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlotOfObjectMapper {

    List<PlotOfObject> mapToNewPlotOfObject(List<NewPlotOfObjectDto> plotOfObjectsDto);

    List<PlotOfObject> mapToUpdatePlotOfObject(List<UpdatePlotOfObjectDto> plotOfObjectsDto);
}