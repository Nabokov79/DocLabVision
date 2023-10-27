package ru.nabokovsg.dataservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.defect.NewDefectDto;
import ru.nabokovsg.dataservice.dto.defect.UpdateDefectDto;
import ru.nabokovsg.dataservice.dto.sizeParameters.NewSizeParametersDto;
import ru.nabokovsg.dataservice.dto.sizeParameters.UpdateSizeParametersDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.dto.defect.DefectDto;
import ru.nabokovsg.dataservice.mappers.DefectMapper;
import ru.nabokovsg.dataservice.models.Defect;
import ru.nabokovsg.dataservice.models.ObjectsType;
import ru.nabokovsg.dataservice.models.SizeParameters;
import ru.nabokovsg.dataservice.repository.DefectRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefectsServiceImpl implements DefectsService {

    private final DefectRepository repository;
    private final DefectMapper mapper;
    private final DefectParameterService parameterService;
    private final ObjectsTypeService objectsTypeService;

    @Override
    public List<DefectDto> save(List<Long> objectsTypeId, List<NewDefectDto> defectsDto) {
        List<SizeParameters> parameters = parameterService.save(defectsDto.stream()
                                                                          .map(NewDefectDto::getParameters)
                                                                          .flatMap(Collection::stream)
                                                                          .toList());
        List<Defect> defectsDb = new ArrayList<>();
        List<ObjectsType> objectsTypes = objectsTypeService.getAll(objectsTypeId);
        for (ObjectsType type : objectsTypes) {
            defectsDb.addAll(defectsDto.stream()
                    .map(d -> {
                        Defect defect = mapper.mapToNewDefect(d);
                        defect.setObjectsType(type);
                        List<String> parametersNames = d.getParameters().stream()
                                .map(NewSizeParametersDto::getParametersName)
                                .toList();
                        defect.setParameters(parameters.stream()
                                .filter(p -> parametersNames.contains(p.getParametersName()))
                                .toList());
                        return defect;
                    })
                    .toList());

        }
        return mapper.mapToDefectDto(repository.saveAll(defectsDb));
    }

    @Override
    public List<DefectDto> update(List<UpdateDefectDto> defectsDto) {
        validateIds(defectsDto.stream().map(UpdateDefectDto::getId).toList());
        List<SizeParameters> parameters = parameterService.update(defectsDto.stream()
                                                                            .map(UpdateDefectDto::getParameters)
                                                                            .flatMap(Collection::stream)
                                                                            .toList());
        List<Defect> defects = defectsDto.stream().map(d -> {
                                                   Defect defect = mapper.mapToUpdateDefect(d);
                                                   List<String> parametersNames = d.getParameters().stream()
                                                           .map(UpdateSizeParametersDto::getParametersName)
                                                           .toList();
                                                   defect.setParameters(parameters.stream()
                                                           .filter(p -> parametersNames.contains(p.getParametersName()))
                                                           .toList());
                                                   return defect;
                                              }).toList();
        return mapper.mapToDefectDto(repository.saveAll(defects));
    }

    private void validateIds(List<Long> ids) {
        Map<Long, Defect> defects = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(Defect::getId, d -> d));
        if (defects.size() != ids.size() || defects.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(defects.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("defects with ids= %s not found", ids));
        }
    }
}