package ru.nabokovsg.dataservice.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.norms.NewNormsDto;
import ru.nabokovsg.dataservice.dto.norms.NormSearchParametersDto;
import ru.nabokovsg.dataservice.dto.norms.UpdateNormsDto;
import ru.nabokovsg.dataservice.dto.objectsType.ObjectsTypeNormDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.dto.norms.NormsDto;
import ru.nabokovsg.dataservice.mappers.NormsMapper;
import ru.nabokovsg.dataservice.models.*;
import ru.nabokovsg.dataservice.repository.ElementRepository;
import ru.nabokovsg.dataservice.repository.NormsRepository;

import javax.persistence.EntityManager;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NormsServiceImpl implements NormsService {

    private final NormsRepository repository;
    private final NormsMapper mapper;
    private final ElementRepository elementRepository;
    private final ObjectsTypeService objectsTypeService;
    private final EntityManager entityManager;

    @Override
    public List<ObjectsTypeNormDto> save(List<Long> objectsTypeId, List<NewNormsDto> normsDto) {
        Map<Long, Element> elementsDb = elementRepository.findAllByObjectsTypeId(objectsTypeId).stream()
                .collect(Collectors.toMap(Element::getId, e -> e));
        Map<Long, SubElement> subElementsDb = elementsDb.values().stream()
                .map(Element::getSubElements)
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toMap(SubElement::getId, s -> s));
        List<Norm> norms = new ArrayList<>();
        for (NewNormsDto normDto : normsDto) {
            Norm norm = getByPredicate(mapper.mapToNormSearchParameters(normDto));
            if (norm != null) {
                norms.add(norm);
            } else {
                norm = mapper.mapToNewNormsDto(normDto);
                norm.setElement(elementsDb.get(normDto.getElementId()));
                if (normDto.getSubElementId() != null) {
                    norm.setSubElement(subElementsDb.get(normDto.getSubElementId()));
                }
                norms.add(repository.save(norm));
            }
        }
        return objectsTypeService.addNorms(objectsTypeId, norms);
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

    private Norm getByPredicate(NormSearchParametersDto parameters) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (parameters.getElementId() != null) {
            booleanBuilder.and(QNorm.norm.element.id.eq(parameters.getElementId()));
        }
        if (parameters.getSubElementId() != null) {
            booleanBuilder.and(QNorm.norm.subElement.id.eq(parameters.getSubElementId()));
        }
        if (parameters.getDiameter() != null) {
            booleanBuilder.and(QNorm.norm.diameter.eq(parameters.getDiameter()));
        }
        if (parameters.getThickness() != null) {
            booleanBuilder.and(QNorm.norm.thickness.eq(parameters.getThickness()));
        }
        QNorm norm = QNorm.norm;
        return new JPAQueryFactory(entityManager).from(norm).select(norm).where(booleanBuilder).fetchOne();
    }
}