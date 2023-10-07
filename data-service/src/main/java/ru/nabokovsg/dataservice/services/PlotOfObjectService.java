package ru.nabokovsg.dataservice.services;

import ru.nabokovsg.dataservice.dto.plotOfObject.NewPlotOfObjectDto;
import ru.nabokovsg.dataservice.dto.plotOfObject.UpdatePlotOfObjectDto;
import ru.nabokovsg.dataservice.models.PlotOfObject;

import java.util.List;

public interface PlotOfObjectService {

    List<PlotOfObject> save(List<NewPlotOfObjectDto> plotOfObjectsDto);

    List<PlotOfObject> update(List<UpdatePlotOfObjectDto> plotOfObjectsDto);
}