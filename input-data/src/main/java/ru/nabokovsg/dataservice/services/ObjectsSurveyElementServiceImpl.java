package ru.nabokovsg.dataservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.objectsSurveyElement.NewObjectsSurveyElementDto;
import ru.nabokovsg.dataservice.dto.objectsSurveyElement.UpdateObjectsSurveyElementDto;
import ru.nabokovsg.dataservice.dto.objectsSurveyElement.ObjectsSurveyElementDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mappers.ObjectsSurveyElementMapper;
import ru.nabokovsg.dataservice.models.*;
import ru.nabokovsg.dataservice.repository.ObjectsSurveyElementRepository;
import ru.nabokovsg.dataservice.services.builder.RepositoryRequestService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ObjectsSurveyElementServiceImpl implements ObjectsSurveyElementService {

    private final ObjectsSurveyElementRepository repository;
    private final ObjectsSurveyElementMapper mapper;
    private final RepositoryRequestService requestService;

    @Override
    public List<ObjectsSurveyElementDto> save(Long surveyObjectId, List<NewObjectsSurveyElementDto> elementsDataDto) {
        ObjectsSurveyElementData data = getDataForObjectsSurveyElement(surveyObjectId
                                                                     , elementsDataDto
                                                                          .stream()
                                                                          .map(NewObjectsSurveyElementDto::getElementId)
                                                                          .toList());
        return mapper.mapFromObjectsSurveyElementDto(repository.saveAll(elementsDataDto.stream().map(e -> {
            SurveyObjectElement elementData = mapper.mapFromNewObjectsSurveyElement(e);
            elementData.setElement(data.getElements().get(e.getElementId()));
            elementData.setSubElement(data.getSubElements().get(e.getSubElementId()));
            elementData.setObjectSurvey(data.getObjectSurvey());
            return elementData;
        }).toList()));
    }

    @Override
    public List<ObjectsSurveyElementDto> update(Long surveyObjectId, List<UpdateObjectsSurveyElementDto> elementsDataDto) {
        validateIds(elementsDataDto.stream().map(UpdateObjectsSurveyElementDto::getId).toList());
        ObjectsSurveyElementData data = getDataForObjectsSurveyElement(surveyObjectId
                                                               , elementsDataDto
                                                                       .stream()
                                                                       .map(UpdateObjectsSurveyElementDto::getElementId)
                                                                       .toList());
        return mapper.mapFromObjectsSurveyElementDto(repository.saveAll(elementsDataDto.stream().map(e -> {
            SurveyObjectElement elementData = mapper.mapFromUpdateObjectsSurveyElement(e);
            elementData.setElement(data.getElements().get(e.getElementId()));
            elementData.setSubElement(data.getSubElements().get(e.getSubElementId()));
            elementData.setObjectSurvey(data.getObjectSurvey());
            return elementData;
        }).toList()));
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("ObjectsSurvey elements with ids= %s not found for delete", id));
    }

    private ObjectsSurveyElementData getDataForObjectsSurveyElement(Long surveyObjectId, List<Long> elementIds) {
        ObjectsSurveyElementData data = new ObjectsSurveyElementData();
        data.setObjectSurvey(requestService.getSurveyObject(surveyObjectId));
        data.setElements(requestService.getElement(elementIds).stream()
                                                            .collect(Collectors.toMap(Element::getId, e -> e)));
        data.setSubElements(data.getElements().values().stream()
                .map(Element::getSubElements)
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toMap(SubElement::getId, s -> s)));
        return data;
    }


    private void validateIds(List<Long> ids) {
        Map<Long, SurveyObjectElement> elements = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(SurveyObjectElement::getId, e -> e));
        if (elements.size() != ids.size() || elements.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(elements.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("ObjectsSurvey elements with ids= %s not found", ids));
        }
    }
}