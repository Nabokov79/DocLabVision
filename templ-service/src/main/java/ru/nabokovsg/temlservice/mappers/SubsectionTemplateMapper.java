package ru.nabokovsg.temlservice.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.temlservice.dto.subsection.NewProtocolSubsectionTemplate;
import ru.nabokovsg.temlservice.dto.subsection.NewSectionSubsectionTemplateDto;
import ru.nabokovsg.temlservice.dto.subsection.SubsectionTemplateDto;
import ru.nabokovsg.temlservice.models.SubsectionTemplate;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubsectionTemplateMapper {

    SubsectionTemplate mapToNewSubsectionTemplate(NewSectionSubsectionTemplateDto subsectionTemplate);

    List<SubsectionTemplate> mapToNewSubsectionTemplateToProtocol(List<NewProtocolSubsectionTemplate> subsectionTemplates);

}
