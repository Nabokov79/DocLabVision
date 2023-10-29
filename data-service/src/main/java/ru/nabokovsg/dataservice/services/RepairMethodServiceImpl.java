package ru.nabokovsg.dataservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.objectsType.ObjectsTypeRepairMethodDto;
import ru.nabokovsg.dataservice.dto.repairMethod.NewRepairMethodDto;
import ru.nabokovsg.dataservice.dto.repairMethod.RepairMethodDto;
import ru.nabokovsg.dataservice.dto.repairMethod.UpdateRepairMethodDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mappers.RepairMethodMapper;
import ru.nabokovsg.dataservice.models.RepairMethod;
import ru.nabokovsg.dataservice.repository.RepairMethodRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RepairMethodServiceImpl implements RepairMethodService {

    private final RepairMethodRepository repository;
    private final RepairMethodMapper mapper;
    private final ObjectsTypeService objectsTypeService;

    @Override
    public List<ObjectsTypeRepairMethodDto> save(List<Long> objectsTypeId, List<NewRepairMethodDto> methodsDto) {
        Map<String, RepairMethod> methodsDb = repository.findAllByMethodName((
                        methodsDto.stream()
                                .map(NewRepairMethodDto::getMethodName)
                                .toList()))
                .stream()
                .collect(Collectors.toMap(RepairMethod::getMethodName, r -> r));
        List<RepairMethod> methods = mapper.mapToNewRepairMethod(methodsDto);
        if (!methodsDb.isEmpty()) {
            methods = repository.saveAll(methods.stream()
                    .filter(m -> !methodsDb.containsKey(m.getMethodName()))
                    .toList());
            methods.addAll(methodsDb.values());
        }
        return objectsTypeService.addRepairMethods(objectsTypeId, repository.saveAll(methods));
    }

    @Override
    public List<RepairMethodDto> update(List<UpdateRepairMethodDto> methodsDto) {
        validateIds(methodsDto.stream().map(UpdateRepairMethodDto::getId).toList());
        return mapper.mapToNewRepairMethodDto(repository.saveAll(mapper.mapToUpdateRepairMethod(methodsDto)));
    }

    private void validateIds(List<Long> ids) {
        Map<Long, RepairMethod> methods = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(RepairMethod::getId, n -> n));
        if (methods.size() != ids.size() || methods.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(methods.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("Repair methods with ids= %s not found", ids));
        }
    }
}