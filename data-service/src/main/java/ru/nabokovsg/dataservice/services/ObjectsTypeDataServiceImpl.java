package ru.nabokovsg.dataservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.objectsTypeData.ObjectsTypeDataDto;
import ru.nabokovsg.dataservice.exceptions.BadRequestException;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mappers.ObjectsTypeDataMapper;
import ru.nabokovsg.dataservice.models.*;
import ru.nabokovsg.dataservice.models.DataBuilder;
import ru.nabokovsg.dataservice.repository.ObjectsTypeDataRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ObjectsTypeDataServiceImpl implements ObjectsTypeDataService {

    private final ObjectsTypeDataRepository repository;
    private final ObjectsTypeService objectsTypeService;
    private final ObjectsTypeDataMapper mapper;


    @Override
    public List<ObjectsTypeData> save(DataBuilder builder) {
        Map<Long, ObjectsTypeData> objectsTypeData = getAllByObjectsType(builder.getObjectsTypeIds());
        switch (builder.getType()) {
            case ELEMENT -> {
                for (Long objectsTypeId : builder.getObjectsTypeIds()) {
                    ObjectsTypeData data = objectsTypeData.get(objectsTypeId);
                    if (data.getElements() == null) {
                        data.setElements(builder.getElements().stream()
                                                              .filter(e -> e.getObjectsTypeId().equals(objectsTypeId))
                                                              .toList());
                    } else {
                        List<Element> elements =  builder.getElements().stream()
                                                                        .filter(e -> !data.getElements().stream()
                                                                                .map(Element::getElementName)
                                                                                .toList().contains(e.getElementName()))
                                                                        .toList();
                        if(!elements.isEmpty()) {
                            data.getElements().addAll(builder.getElements().stream()
                                                                            .filter(e -> e.getObjectsTypeId().equals(objectsTypeId))
                                                                            .toList());
                        }
                    }
                }
            }
            case DEFECT -> {
                for (Long objectsTypeId : builder.getObjectsTypeIds()) {
                    ObjectsTypeData data = objectsTypeData.get(objectsTypeId);
                    if (data.getDefects() == null) {
                        data.setDefects(builder.getDefects());
                    } else {
                        data.getDefects().addAll(builder.getDefects().stream()
                                                                     .filter(e -> !data.getDefects().stream()
                                                                             .map(Defect::getDefectName)
                                                                             .toList().contains(e.getDefectName()))
                                                                     .toList());
                    }
                }
            }
            case NORM -> {
                for (Long objectsTypeId : builder.getObjectsTypeIds()) {
                    ObjectsTypeData data = objectsTypeData.get(objectsTypeId);
                    if (data.getNorms() == null) {
                        data.setNorms(builder.getNorms());
                    } else {
                        data.getNorms().addAll(builder.getNorms()
                                            .stream()
                                            .filter(n -> !data.getNorms().stream()
                                                                         .map(Norm::getId)
                                                                         .toList()
                                                    .contains(n.getId()))
                                            .toList());
                    }
                }
            }
            case DOCUMENTATION -> {
                for (Long objectsTypeId : builder.getObjectsTypeIds()) {
                    ObjectsTypeData data = objectsTypeData.get(objectsTypeId);
                    if (data.getDocumentations() == null) {
                        data.setDocumentations(builder.getDocumentations());
                    } else {
                        data.getDocumentations().addAll(builder.getDocumentations()
                                                                .stream()
                                                                .filter(d -> !data.getDocumentations()
                                                                                    .stream()
                                                                                    .map(Documentation::getId)
                                                                                    .toList()
                                                                        .contains(d.getId()))
                                                                .toList());
                    }
                }
            }
            case REPAIR_METHOD -> {
                for (Long objectsTypeId : builder.getObjectsTypeIds()) {
                    ObjectsTypeData data = objectsTypeData.get(objectsTypeId);
                    if (data.getRepairMethods() == null) {
                        data.setRepairMethods(builder.getMethods());
                    } else {
                        data.getRepairMethods().addAll(builder.getMethods()
                                                              .stream()
                                                              .filter(m -> !data.getRepairMethods()
                                                                                  .stream()
                                                                                  .map(RepairMethod::getId)
                                                                                  .toList()
                                                                      .contains(m.getId()))
                                                              .toList());
                    }
                }
            }
            case TEMPLATE -> {
                for (Long objectsTypeId : builder.getObjectsTypeIds()) {
                    ObjectsTypeData data = objectsTypeData.get(objectsTypeId);
                    if (data.getTemplates() == null) {
                        data.setTemplates(builder.getTemplates());
                    } else {
                        data.getTemplates().addAll(builder.getTemplates()
                                                            .stream()
                                                            .filter(t -> !data.getTemplates()
                                                                                .stream()
                                                                                .map(ObjectPassportDataTemplate::getId)
                                                                                .toList()
                                                                    .contains(t.getId()))
                                                            .toList());
                    }
                }
            }
            default -> throw new BadRequestException(
                    String.format("type data %s is not supported", builder.getType()));
        }
        return repository.saveAll(objectsTypeData.values());
    }

    @Override
    public ObjectsTypeDataDto get(Long id) {
        return mapper.mapToObjectsTypeDataDto(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("ObjectsTypeData with id=%s not found", id))));
    }

    private Map<Long, ObjectsTypeData> getAllByObjectsType(List<Long> ids) {
        if (!ids.isEmpty()) {
            List<ObjectsType> objectsTypes = objectsTypeService.getAll(ids);
            if(objectsTypes.isEmpty()) {
                throw new NotFoundException(String.format("ObjectsType with ids = %s not found", ids));
            }
            Map<Long, ObjectsTypeData> data = repository.findAllByObjectsType(objectsTypes).stream()
                    .collect(Collectors.toMap(o -> o.getObjectsType().getId(), o -> o));
            for (ObjectsType type : objectsTypes) {
                if (data.get(type.getId()) == null) {
                    ObjectsTypeData typeData = new ObjectsTypeData();
                    typeData.setObjectsType(type);
                    data.put(type.getId(), typeData);
                }
            }
            return data;
        }
       throw new BadRequestException(String.format("ObjectsType ids = %s should not be empty", ids));
    }
}