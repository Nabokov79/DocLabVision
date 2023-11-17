package ru.nabokovsg.dataservice.services;

import org.springframework.validation.annotation.Validated;
import ru.nabokovsg.dataservice.dto.subElement.NewSubElementDto;
import ru.nabokovsg.dataservice.dto.subElement.UpdateSubElementDto;
import ru.nabokovsg.dataservice.models.SubElement;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface SubElementService {

    List<SubElement> save(@Valid List<NewSubElementDto> subElementsDto);

    List<SubElement> update(@Valid List<UpdateSubElementDto> subElementsDto);
}