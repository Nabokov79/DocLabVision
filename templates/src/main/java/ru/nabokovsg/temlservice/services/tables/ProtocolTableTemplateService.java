package ru.nabokovsg.temlservice.services.tables;

import ru.nabokovsg.temlservice.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.temlservice.dto.table.NewProtocolTableTemplateDto;

public interface ProtocolTableTemplateService {

    ProtocolTemplateDto save(NewProtocolTableTemplateDto templateDto);
}
