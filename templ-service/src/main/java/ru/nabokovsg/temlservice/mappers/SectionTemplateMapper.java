package ru.nabokovsg.temlservice.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.temlservice.dto.section.NewSectionTemplateDto;
import ru.nabokovsg.temlservice.dto.section.SectionTemplateDto;
import ru.nabokovsg.temlservice.models.SectionTemplate;

@Mapper(componentModel = "spring")
public interface SectionTemplateMapper {

    SectionTemplate mapToNewSectionTemplate(NewSectionTemplateDto sectionTemplateDto);

    SectionTemplateDto mapToSectionTemplateDto(SectionTemplate sectionTemplate);
}
