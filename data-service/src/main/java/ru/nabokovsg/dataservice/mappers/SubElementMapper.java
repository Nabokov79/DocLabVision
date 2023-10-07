package ru.nabokovsg.dataservice.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.subElement.NewSubElementDto;
import ru.nabokovsg.dataservice.dto.subElement.UpdateSubElementDto;
import ru.nabokovsg.dataservice.models.SubElement;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubElementMapper {

    SubElement mapToNewSubElement(NewSubElementDto subElementDto);

    List<SubElement> mapToNewSubElements(List<NewSubElementDto> subElementsDto);

    List<SubElement> mapToUpdateSubElements(List<UpdateSubElementDto> subElementsDto);
}