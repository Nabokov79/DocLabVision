package ru.nabokovsg.temlservice.services;

import org.springframework.validation.annotation.Validated;
import ru.nabokovsg.temlservice.dto.header.NewHeaderTemplateDto;
import ru.nabokovsg.temlservice.dto.header.NewPageTitleHeaderTemplateDto;
import ru.nabokovsg.temlservice.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.temlservice.models.HeaderTemplate;

@Validated
public interface HeaderTemplateService {

    ProtocolTemplateDto save(NewHeaderTemplateDto headerDto);

    HeaderTemplate getHeader( NewPageTitleHeaderTemplateDto headerDto);
}