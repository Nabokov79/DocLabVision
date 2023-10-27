package ru.nabokovsg.dataservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.element.ElementDto;
import ru.nabokovsg.dataservice.dto.element.NewElementDto;
import ru.nabokovsg.dataservice.dto.element.UpdateElementDto;
import ru.nabokovsg.dataservice.dto.objectsType.ObjectsTypeDto;
import ru.nabokovsg.dataservice.dto.subElement.NewSubElementDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mappers.ElementMapper;
import ru.nabokovsg.dataservice.mappers.ObjectsTypeMapper;
import ru.nabokovsg.dataservice.models.*;
import ru.nabokovsg.dataservice.repository.ElementRepository;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ElementServiceImpl implements ElementService {

    private final ElementRepository repository;
    private final ElementMapper mapper;
    private final SubElementService subElementService;
    private final ObjectsTypeService objectsTypeService;
    private final ObjectsTypeMapper objectsTypeMapper;


    @Override
    public List<ElementDto> save(List<Long> objectsTypeId, List<NewElementDto> elementsDto) {
        List<SubElement> subElementsDb = subElementService.save(elementsDto.stream()
                                                                            .map(NewElementDto::getSubElements)
                                                                            .filter(Objects::nonNull)
                                                                            .flatMap(Collection::stream)
                                                                            .toList());
        List<ObjectsType> objectsTypes = objectsTypeService.getAll(objectsTypeId);
        List<Element> elementsDb = new ArrayList<>();
        for (ObjectsType type : objectsTypes) {
            elementsDb.addAll(elementsDto.stream()
                    .map(e -> {
                        Element element = mapper.mapToNewElement(e);
                        element.setObjectsType(type);
                        if (e.getSubElements() != null) {
                            List<String> subElementNames = e.getSubElements().stream()
                                    .map(NewSubElementDto::getSubElementName)
                                    .toList();
                            element.setSubElements(subElementsDb.stream()
                                    .filter(s -> subElementNames.contains(s.getSubElementName()))
                                    .toList());
                        }
                        return element;
                    })
                    .toList());

        }
       return mapper.mapToElementDto(repository.saveAll(elementsDb));
    }

    @Override
    public List<ElementDto> update(List<UpdateElementDto> elementsDto) {
        validateIds(elementsDto.stream().map(UpdateElementDto::getId).toList());
        List<SubElement> subElementsDb = subElementService.update(elementsDto.stream()
                .map(UpdateElementDto::getSubElements)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .toList());
        Map<Long, ObjectsType> objectsTypes = objectsTypeService.getAll(elementsDto.stream()
                                                                .map(UpdateElementDto::getObjectsTypeId)
                                                                .toList())
                                                                .stream()
                                                                .collect(Collectors.toMap(ObjectsType::getId, o -> o));
        List<Element> elementsDb = elementsDto.stream()
                                              .map(e -> {
                                                  Element element = mapper.mapToUpdateElement(e);
                                                  element.setObjectsType(objectsTypes.get(e.getObjectsTypeId()));
                                                  List<String> subElementNames = element.getSubElements().stream()
                                                          .map(SubElement::getSubElementName)
                                                          .toList();
                                                  element.setSubElements(subElementsDb.stream()
                                                          .filter(s -> subElementNames.contains(s.getSubElementName()))
                                                          .toList());
                                                    return element;
                                                })
                                                .toList();
        return mapper.mapToElementDto(repository.saveAll(elementsDb));
    }

    @Override
    public ObjectsTypeDto getAll(Long objectsTypeId) {
        ObjectsType objectsTypeDb = objectsTypeService.getById(objectsTypeId);
        ObjectsTypeDto objectsType = objectsTypeMapper.mapToObjectTypeDto(objectsTypeDb);
        objectsType.setElements(
                mapper.mapToElementDto(new ArrayList<>(repository.findAllByObjectsType(List.of(objectsTypeDb)))));
        return objectsType;
    }

    private void validateIds(List<Long> ids) {
        Map<Long, Element> elements = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(Element::getId, n -> n));
        if (elements.size() != ids.size() || elements.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(elements.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(toList());
            throw new NotFoundException(String.format("elements with ids= %s not found", ids));
        }
    }
}