package ru.nabokovsg.dataservice.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.subElement.NewSubElementDto;
import ru.nabokovsg.dataservice.dto.subElement.UpdateSubElementDto;
import ru.nabokovsg.dataservice.models.SubElement;

@Mapper(componentModel = "spring")
public interface SubElementMapper {

    SubElement mapToNewSubElement(NewSubElementDto subElementDto);

   SubElement mapToUpdateSubElements(UpdateSubElementDto subElementDto);
}