package ru.nabokovsg.temlservice.services.passportData;

import ru.nabokovsg.temlservice.dto.passportData.NewProtocolPassportDataTemplateDto;
import ru.nabokovsg.temlservice.dto.protocol.ProtocolTemplateDto;

public interface ProtocolPassportDataTemplateService {

    ProtocolTemplateDto save(NewProtocolPassportDataTemplateDto passportDataDto);
}
