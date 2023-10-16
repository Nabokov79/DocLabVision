package ru.nabokovsg.dataservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.subElement.NewSubElementDto;
import ru.nabokovsg.dataservice.dto.subElement.UpdateSubElementDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mappers.SubElementMapper;
import ru.nabokovsg.dataservice.models.SubElement;
import ru.nabokovsg.dataservice.repository.SubElementRepository;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubElementServiceImpl implements SubElementService {

    private final SubElementRepository repository;
    private final SubElementMapper mapper;


    @Override
    public List<SubElement> save(List<NewSubElementDto> subElementsDto) {
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
                                                                            .map(mapper::mapToNewSubElement)
                                                                            .toList());
            subElements.addAll(subElementsDb.values());
            return subElements;
        }
    }

    @Override
    public List<SubElement> update(List<UpdateSubElementDto> subElementsDto) {
        validateIds(subElementsDto.stream().map(UpdateSubElementDto::getId).toList());
        return filterSubElementByDuplicate(subElementsDto.stream().map(mapper::mapToUpdateSubElements).toList());
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

    private List<SubElement> filterSubElementByDuplicate(List<SubElement> subElements) {
        Set<SubElement> subElementsDb = repository.findAllBySubElementName(subElements.stream()
                                                                                    .map(SubElement::getSubElementName)
                                                                                    .toList());
        if (!subElementsDb.isEmpty()) {
            List<String> subElementNames = subElements.stream().map(SubElement::getSubElementName).toList();
            subElements = subElements.stream()
                                     .filter(s -> !subElementNames.contains(s.getSubElementName()))
                                     .toList();
        }
        return subElements;
    }
}