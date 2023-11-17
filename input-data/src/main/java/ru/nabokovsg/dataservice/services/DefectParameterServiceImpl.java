package ru.nabokovsg.dataservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.sizeParameters.NewSizeParametersDto;
import ru.nabokovsg.dataservice.dto.sizeParameters.UpdateSizeParametersDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mappers.DefectParameterMapper;
import ru.nabokovsg.dataservice.models.SizeParameters;
import ru.nabokovsg.dataservice.repository.DefectParameterRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefectParameterServiceImpl implements DefectParameterService {

    private final DefectParameterRepository repository;
    private final DefectParameterMapper mapper;

    @Override
    public List<SizeParameters> save(List<NewSizeParametersDto> parametersDto) {
        parametersDto = parametersDto.stream().distinct().collect(Collectors.toList());
        Map<String, SizeParameters> parameters = repository.findAllDefectParameterByParametersName(
                                                                 (parametersDto.stream()
                                                                          .map(NewSizeParametersDto::getParametersName)
                                                                          .distinct()
                                                                          .toList())
                                                        )
                                        .stream().collect(Collectors.toMap(SizeParameters::getParametersName, d -> d));
        if (parameters.isEmpty()) {
            return repository.saveAll(mapper.mapToNewDefectParameter(parametersDto));
        } else {
            parametersDto = parametersDto.stream().filter(p -> !parameters.containsKey(p.getParametersName())).toList();
            if (!parametersDto.isEmpty()) {
                List<SizeParameters> parametersDb = repository.saveAll(mapper.mapToNewDefectParameter(parametersDto));
                for (SizeParameters parameter : parametersDb) {
                    parameters.put(parameter.getParametersName(), parameter);
                }
            }
            return parameters.values().stream().toList();
        }
    }

    @Override
    public List<SizeParameters> update(List<UpdateSizeParametersDto> parametersDto) {
        validateIds(parametersDto.stream().map(UpdateSizeParametersDto::getId).distinct().toList());
            return repository.saveAll(mapper.mapToUpdateDefectParameter(parametersDto));
    }

    private void validateIds(List<Long> ids) {
        Map<Long, SizeParameters> parameters = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(SizeParameters::getId, d -> d));
        if (parameters.size() != ids.size() || parameters.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(parameters.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("parameters with ids= %s not found", ids));
        }
    }
}