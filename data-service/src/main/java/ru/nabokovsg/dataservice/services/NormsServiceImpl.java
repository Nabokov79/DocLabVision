package ru.nabokovsg.dataservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.norms.NewNormsDto;
import ru.nabokovsg.dataservice.dto.norms.UpdateNormsDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.dto.norms.NormsDto;
import ru.nabokovsg.dataservice.mappers.NormsMapper;
import ru.nabokovsg.dataservice.models.*;
import ru.nabokovsg.dataservice.repository.ElementRepository;
import ru.nabokovsg.dataservice.repository.NormsRepository;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NormsServiceImpl implements NormsService {

    private final NormsRepository repository;
    private final NormsMapper mapper;
    private final ElementRepository elementRepository;
    private final ObjectsTypeService objectsTypeService;

    @Override
    public List<NormsDto> save(List<Long> objectsTypeId, List<NewNormsDto> normsDto) {
        List<ObjectsType> objectsTypes = objectsTypeService.getAll(objectsTypeId);
        Map<Long, Element> elements = elementRepository.findAllByObjectsType(objectsTypes)
                                                                    .stream()
                                                                    .collect(Collectors.toMap(Element::getId, e -> e));
        Map<Long, SubElement> subElements = elements.values().stream()
                                                                 .map(Element::getSubElements)
                                                                 .flatMap(Collection::stream)
                                                                 .distinct()
                                                                 .collect(Collectors.toMap(SubElement::getId, s -> s));

        List<Norm> norms = new ArrayList<>();
        for (ObjectsType type : objectsTypes) {
            norms.addAll(normsDto.stream()
                                 .map(n -> {
                                            Norm norm = mapper.mapToNewNormsDto(n);
                                            norm.setElement(elements.get(n.getElementId()));
                                            norm.setSubElement(subElements.get(n.getSubElementId()));
                                            norm.setObjectsType(type);
                                            return norm;
                                        })
                                .toList());
        }
        return mapper.mapToNormsDto(repository.saveAll(norms));
    }

    @Override
    public List<NormsDto> update(List<UpdateNormsDto> normsDto) {
        validateIds(normsDto.stream().map(UpdateNormsDto::getId).toList());
        Map<Long, Element> elements = elementRepository.findAllById(normsDto.stream()
                                                                            .map(UpdateNormsDto::getElementId)
                                                                            .toList())
                                                                    .stream()
                                                                    .collect(Collectors.toMap(Element::getId, e -> e));
        Map<Long, SubElement> subElements = elements.values().stream()
                                                             .map(Element::getSubElements)
                                                             .flatMap(Collection::stream)
                                                             .collect(Collectors.toMap(SubElement::getId, s -> s));
        return mapper.mapToNormsDto(repository.saveAll(normsDto.stream()
                .map(n -> {
                    Norm norm = mapper.mapToUpdateNormsDto(n);
                    norm.setElement(elements.get(n.getElementId()));
                    norm.setSubElement(subElements.get(n.getSubElementId()));
                    return norm;
                })
                .toList()));
    }

    private void validateIds(List<Long> ids) {
        Map<Long, Norm> norms = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(Norm::getId, n -> n));
        if (norms.size() != ids.size() || norms.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(norms.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("Norms with ids= %s not found", ids));
        }
    }
}