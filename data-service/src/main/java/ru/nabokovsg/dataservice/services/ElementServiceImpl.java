package ru.nabokovsg.dataservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.element.ElementDto;
import ru.nabokovsg.dataservice.dto.element.NewElementDto;
import ru.nabokovsg.dataservice.dto.element.UpdateElementDto;
import ru.nabokovsg.dataservice.dto.objectsType.ObjectsTypeElementsDto;
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
    public List<ObjectsTypeElementsDto> save(List<Long> objectsTypeId, List<NewElementDto> elementsDto) {
        List<SubElement> subElementsDb = subElementService.save(elementsDto.stream()
                                                                            .map(NewElementDto::getSubElements)
                                                                            .filter(Objects::nonNull)
                                                                            .flatMap(Collection::stream)
                                                                            .toList());
        List<Element> elementsDb = new ArrayList<>(repository.findAllByObjectsTypeId(objectsTypeId));
        List<Element> elements = new ArrayList<>();
        for (Long id : objectsTypeId) {
            List<Element> elementsDbUpd = elementsDb.stream()
                    .filter(e -> e.getObjectsTypeId().equals(id))
                    .toList();
            if (elementsDbUpd.isEmpty()) {
                elements.addAll(repository.saveAll(elementsDto.stream().map(e -> {
                    Element element = mapper.mapToNewElement(e);
                    element.setObjectsTypeId(id);
                    if (e.getSubElements() != null) {
                        List<String> subElementNames = element.getSubElements().stream()
                                .map(SubElement::getSubElementName)
                                .toList();
                        element.setSubElements(subElementsDb.stream()
                                .filter(s -> subElementNames.contains(s.getSubElementName()))
                                .toList());
                    }
                    return element;
                }).toList()));
            } else {
                for (Element e : elementsDbUpd) {
                    if (!e.getSubElements().isEmpty()) {
                        List<String> subElementNames = e.getSubElements().stream()
                                .map(SubElement::getSubElementName)
                                .toList();
                        List<SubElement> subElements = subElementsDb.stream()
                                .filter(s -> !subElementNames.contains(s.getSubElementName()))
                                .toList();
                        if (!subElements.isEmpty()) {
                            e.getSubElements().addAll(subElements);
                        }
                    }
                    elements.addAll(repository.saveAll(elementsDbUpd));
                }
            }
        }
        return objectsTypeService.addElements(objectsTypeId, elements);
    }

    @Override
    public List<ElementDto> update(List<UpdateElementDto> elementsDto) {
        validateIds(elementsDto.stream().map(UpdateElementDto::getId).toList());
        List<SubElement> subElementsDb = subElementService.update(elementsDto.stream()
                .map(UpdateElementDto::getSubElements)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .toList());
        List<Element> elementsDb = elementsDto.stream()
                                              .map(e -> {
                                                  Element element = mapper.mapToUpdateElement(e);
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
    public ObjectsTypeElementsDto getAll(Long objectsTypeId) {
        return objectsTypeMapper.mapToObjectsTypeElementsDto(objectsTypeService.getById(objectsTypeId));
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