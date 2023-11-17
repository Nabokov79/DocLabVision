package ru.nabokovsg.dataservice.services;

import ru.nabokovsg.dataservice.dto.measuringTool.MeasuringToolDto;
import ru.nabokovsg.dataservice.dto.measuringTool.NewMeasuringToolDto;
import ru.nabokovsg.dataservice.dto.measuringTool.RequestParameters;
import ru.nabokovsg.dataservice.dto.measuringTool.UpdateMeasuringToolDto;

import java.util.List;

public interface MeasuringToolService {

    List<MeasuringToolDto> save(List<NewMeasuringToolDto> measuringTools);

    List<MeasuringToolDto> update(List<UpdateMeasuringToolDto> measuringTools);

    List<MeasuringToolDto> getAll(RequestParameters parameters);

    void delete(Long id);
}