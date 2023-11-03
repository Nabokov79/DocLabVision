package ru.nabokovsg.temlservice.services;

import ru.nabokovsg.temlservice.dto.builders.IdsListBuilder;
import ru.nabokovsg.temlservice.dto.protocol.NewProtocolTemplateDto;
import ru.nabokovsg.temlservice.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.temlservice.models.SubsectionTemplate;

import java.util.List;

public interface ProtocolTemplateService {

    ProtocolTemplateDto save(NewProtocolTemplateDto protocolTemplateDto);

    ProtocolTemplateDto addSubsectionTemplate(IdsListBuilder builder,  List<SubsectionTemplate> subsectionTemplate);
}