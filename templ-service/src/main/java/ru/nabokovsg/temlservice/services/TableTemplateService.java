package ru.nabokovsg.temlservice.services;

import ru.nabokovsg.temlservice.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.temlservice.dto.section.SectionTemplateDto;
import ru.nabokovsg.temlservice.dto.table.NewProtocolTableTemplateDto;
import ru.nabokovsg.temlservice.dto.table.NewSubsectionTableTemplateDto;

public interface TableTemplateService {

    SectionTemplateDto addToSubsectionTemplate(NewSubsectionTableTemplateDto templateDto);

    ProtocolTemplateDto addToProtocolTemplate(NewProtocolTableTemplateDto templateDto);
}