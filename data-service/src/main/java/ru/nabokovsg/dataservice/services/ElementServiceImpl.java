package ru.nabokovsg.dataservice.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.element.ElementDto;
import ru.nabokovsg.dataservice.dto.element.NewElementDto;
import ru.nabokovsg.dataservice.dto.element.UpdateElementDto;
import ru.nabokovsg.dataservice.dto.objectsTypeData.ObjectsTypeElementDataDto;
import ru.nabokovsg.dataservice.dto.subElement.UpdateSubElementDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mappers.ElementMapper;
import ru.nabokovsg.dataservice.mappers.ObjectsTypeDataMapper;
import ru.nabokovsg.dataservice.models.*;
import ru.nabokovsg.dataservice.repository.ElementRepository;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Slf4j
public class ElementServiceImpl implements ElementService {

    private final ElementRepository repository;
    private final ElementMapper mapper;
    private final SubElementService subElementService;
    private final ObjectsTypeDataService dataService;
    private final ObjectsTypeDataMapper dataMapper;


    @Override
    public List<ObjectsTypeElementDataDto> save(List<Long> objectsTypeId, List<NewElementDto> elementsDto) {
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
        return dataMapper.mapToObjectsTypeElementDataDto(
                dataService.save(
                        new DataBuilder.Data().type(BuilderType.ELEMENT)
                                .ids(objectsTypeId)
                                .elements(elements)
                                .build()));
    }

    @Override
    public List<ElementDto> update(List<UpdateElementDto> elementsDto) {
        validateIds(elementsDto.stream().map(UpdateElementDto::getId).toList());
        List<SubElement> subElements = subElementService.update(elementsDto.stream()
                .map(UpdateElementDto::getSubElements)
                .flatMap(Collection::stream)
                .toList());
        Map<String, List<String>> subElementNames = elementsDto
                .stream()
                .collect(Collectors.toMap(UpdateElementDto::getElementName, s -> s.getSubElements()
                        .stream().map(UpdateSubElementDto::getSubElementName)
                        .toList()));
        return mapper.mapToElementDto(
                repository.saveAll(
                        mapper.mapToUpdateElement(elementsDto)
                                .stream()
                                .peek(e -> e.setSubElements(subElements
                                        .stream()
                                        .filter(sub -> subElementNames.get(e.getElementName()).contains(sub.getSubElementName()))
                                        .toList()))
                                .toList()));
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