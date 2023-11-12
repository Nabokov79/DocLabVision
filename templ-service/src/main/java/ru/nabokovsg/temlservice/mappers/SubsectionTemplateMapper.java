package ru.nabokovsg.temlservice.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.temlservice.dto.subsection.NewSubsectionDataDto;
import ru.nabokovsg.temlservice.dto.subsection.NewSubsectionTemplateDto;
import ru.nabokovsg.temlservice.models.SubsectionTemplate;

@Mapper(componentModel = "spring")
public interface SubsectionTemplateMapper {

    SubsectionTemplate mapToNewSubsectionTemplate(NewSubsectionTemplateDto subsectionDto);

    NewSubsectionDataDto mapToNewSubsectionDataTemplate(NewSubsectionTemplateDto subsectionDto);
}