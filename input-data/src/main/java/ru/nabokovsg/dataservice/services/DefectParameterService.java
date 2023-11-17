package ru.nabokovsg.dataservice.services;

import org.springframework.validation.annotation.Validated;
import ru.nabokovsg.dataservice.dto.sizeParameters.NewSizeParametersDto;
import ru.nabokovsg.dataservice.dto.sizeParameters.UpdateSizeParametersDto;
import ru.nabokovsg.dataservice.models.SizeParameters;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface DefectParameterService {

    List<SizeParameters> save(@Valid  List<NewSizeParametersDto> parametersDto);

    List<SizeParameters> update(@Valid List<UpdateSizeParametersDto> parametersDto);
}