package ru.nabokovsg.dataservice.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.ObjectsIds;
import ru.nabokovsg.dataservice.dto.measuringTool.MeasuringToolDto;
import ru.nabokovsg.dataservice.dto.measuringTool.NewMeasuringToolDto;
import ru.nabokovsg.dataservice.dto.measuringTool.UpdateMeasuringToolDto;
import ru.nabokovsg.dataservice.models.MeasuringTool;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MeasuringToolMapper {

    MeasuringTool mapToNewMeasuringTool(NewMeasuringToolDto newMeasuringToolDto);

    List<MeasuringToolDto> mapToMeasuringToolDto(List<MeasuringTool> measuringTools);

    MeasuringTool mapToUpdateMeasuringTool(UpdateMeasuringToolDto measuringToolDto);

    List<MeasuringToolDto> mapToMeasuringToolsDto(List<MeasuringTool> newMeasuringTools);

    ObjectsIds mapFromNewMeasuringToolDto(NewMeasuringToolDto measuringToolDto);

    ObjectsIds mapFromUpdateMeasuringToolDto(UpdateMeasuringToolDto measuringToolDto);
}