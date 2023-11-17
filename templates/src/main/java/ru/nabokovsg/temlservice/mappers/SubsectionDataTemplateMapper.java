package ru.nabokovsg.temlservice.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.temlservice.dto.subsection.NewSubsectionDataDto;
import ru.nabokovsg.temlservice.dto.subsection.NewSubsectionTemplateDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubsectionDataTemplateMapper {

    List<NewSubsectionDataDto> mapToNewSubsectionDataDto(List<NewSubsectionTemplateDto> subsections);
}
