package ru.nabokovsg.dataservice.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.element.ElementDto;
import ru.nabokovsg.dataservice.dto.element.NewElementDto;
import ru.nabokovsg.dataservice.dto.element.UpdateElementDto;
import ru.nabokovsg.dataservice.models.Element;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ElementMapper {

    Element mapToNewElement(NewElementDto elementDto);

    Element mapToUpdateElement(UpdateElementDto elementDto);

    List<ElementDto> mapToElementDto(List<Element> elements);
}