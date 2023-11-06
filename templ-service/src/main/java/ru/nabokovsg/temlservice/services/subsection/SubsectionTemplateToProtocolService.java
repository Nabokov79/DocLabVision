package ru.nabokovsg.temlservice.services.subsection;

import ru.nabokovsg.temlservice.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.temlservice.dto.subsection.NewSubsectionTemplateToProtocolDto;

public interface SubsectionTemplateToProtocolService {

    ProtocolTemplateDto save(NewSubsectionTemplateToProtocolDto templateDto);
}
