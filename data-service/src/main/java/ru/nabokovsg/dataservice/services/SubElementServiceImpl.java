package ru.nabokovsg.dataservice.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.place.NewPlaceDto;
import ru.nabokovsg.dataservice.dto.place.UpdatePlaceDto;
import ru.nabokovsg.dataservice.dto.subElement.NewSubElementDto;
import ru.nabokovsg.dataservice.dto.subElement.UpdateSubElementDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mappers.SubElementMapper;
import ru.nabokovsg.dataservice.models.Place;
import ru.nabokovsg.dataservice.models.SubElement;
import ru.nabokovsg.dataservice.repository.SubElementRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubElementServiceImpl implements SubElementService {

    private final SubElementRepository repository;
    private final SubElementMapper mapper;
    private final PlaceService placeService;


    @Override
    public List<SubElement> save(List<NewSubElementDto> subElementsDto) {
        List<Place> places = placeService.save(subElementsDto.stream()
                                                             .map(NewSubElementDto::getPlaces)
                                                             .filter(Objects::nonNull)
                                                             .flatMap(Collection::stream)
                                                             .distinct()
                                                             .toList());
        Map<String, SubElement> subElementsDb = repository.findAllBySubElementName(
                                                                 subElementsDto.stream()
                                                                               .map(NewSubElementDto::getSubElementName)
                                                                               .toList())
                                                      .stream()
                                                      .collect(Collectors.toMap(SubElement::getSubElementName, s -> s));

        if (!subElementsDb.isEmpty()) {
            subElementsDto = subElementsDto.stream().filter(s -> !subElementsDb.containsKey(s.getSubElementName())).toList();
        }
        if (subElementsDto.isEmpty()) {
            return subElementsDb.values().stream().toList();
        } else {
            List<SubElement> subElements = repository.saveAll(subElementsDto.stream()
                                                                            .map(s -> {
                                                                                SubElement sub = mapper.mapToNewSubElement(s);
                                                                                if (s.getPlaces() != null) {
                                                                                    List<String> placeName = s.getPlaces().stream()
                                                                                            .map(NewPlaceDto::getPlaceName)
                                                                                            .toList();
                                                                                    sub.setPlaces(places.stream()
                                                                                            .filter(p -> placeName.contains(p.getPlaceName()))
                                                                                            .toList());
                                                                                }
                                                                                return sub;})
                                                                            .toList());
            subElements.addAll(subElementsDb.values());
            return subElements;
        }
    }

    @Override
    public List<SubElement> update(List<UpdateSubElementDto> subElementsDto) {
        validateIds(subElementsDto.stream().map(UpdateSubElementDto::getId).toList());
        return set(filterSubElementByDuplicate(mapper.mapToUpdateSubElements(subElementsDto))
                , subElementsDto.stream()
                                .collect(Collectors.toMap(UpdateSubElementDto::getSubElementName
                                                        , s -> s.getPlaces().stream()
                                                                            .map(UpdatePlaceDto::getPlaceName)
                                                                            .toList()))
                , placeService.update(subElementsDto.stream()
                                                    .map(UpdateSubElementDto::getPlaces)
                                                    .filter(Objects::nonNull)
                                                    .flatMap(Collection::stream)
                                                    .distinct()
                                                    .toList()));
    }

    private void validateIds(List<Long> ids) {
        Map<Long, SubElement> subElements = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(SubElement::getId, n -> n));
        if (subElements.size() != ids.size() || subElements.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(subElements.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("SubElements with ids= %s not found", ids));
        }
    }
    private List<SubElement> set(Map<String, SubElement> subElementsDb
                               , Map<String, List<String>> placeNames
                               , List<Place> places) {
        log.info("SubElementServiceImpl SET() List<SubElement> subElementsDb = {}", subElementsDb);
        return repository.saveAll(subElementsDb.values().stream()
                .peek(sub -> sub.setPlaces(places.stream()
                                        .filter(p -> placeNames.get(sub.getSubElementName()).contains(p.getPlaceName()))
                                        .toList())
        ).toList());
    }

    private Map<String, SubElement> filterSubElementByDuplicate(List<SubElement> subElements) {
        Set<SubElement> subElementsDb = repository.findAllBySubElementName(subElements.stream()
                                                                                    .map(SubElement::getSubElementName)
                                                                                    .toList());
        if (!subElementsDb.isEmpty()) {
            List<String> subElementNames = subElements.stream().map(SubElement::getSubElementName).toList();
            subElements = subElements.stream()
                                     .filter(s -> !subElementNames.contains(s.getSubElementName()))
                                     .toList();
        }
        log.info("filterSubElementByDuplicate() Set<SubElement> subElementsDb = {}", subElementsDb);
        return subElements.stream().collect(Collectors.toMap(SubElement::getSubElementName, s -> s ));
    }
}