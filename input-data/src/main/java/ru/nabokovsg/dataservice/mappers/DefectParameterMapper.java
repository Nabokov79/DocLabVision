package ru.nabokovsg.dataservice.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.sizeParameters.NewSizeParametersDto;
import ru.nabokovsg.dataservice.dto.sizeParameters.UpdateSizeParametersDto;
import ru.nabokovsg.dataservice.models.SizeParameters;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DefectParameterMapper {

    List<SizeParameters> mapToNewDefectParameter(List<NewSizeParametersDto> parametersDto);

    List<SizeParameters> mapToUpdateDefectParameter(List<UpdateSizeParametersDto> parametersDto);
}