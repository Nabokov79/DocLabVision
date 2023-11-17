package ru.nabokovsg.temlservice.services.subsections.protocols;

import ru.nabokovsg.temlservice.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.temlservice.dto.subsection.NewProtocolSubsectionTemplate;

public interface ProtocolSubsectionTemplateService {

    ProtocolTemplateDto save(NewProtocolSubsectionTemplate subsectionsDto);
}
