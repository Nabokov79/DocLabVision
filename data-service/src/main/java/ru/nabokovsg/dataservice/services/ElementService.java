package ru.nabokovsg.dataservice.services;

import ru.nabokovsg.dataservice.dto.element.ElementDto;
import ru.nabokovsg.dataservice.dto.element.NewElementDto;
import ru.nabokovsg.dataservice.dto.element.UpdateElementDto;
import ru.nabokovsg.dataservice.dto.objectsType.ObjectsTypeDto;

import java.util.List;


public interface ElementService {

    List<ElementDto> save(List<Long> objectsTypeId, List<NewElementDto> elementsDto);

    List<ElementDto> update(List<UpdateElementDto> elementsDto);

    ObjectsTypeDto getAll(Long objectsTypeId);
}