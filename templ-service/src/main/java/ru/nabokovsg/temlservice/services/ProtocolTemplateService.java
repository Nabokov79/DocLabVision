package ru.nabokovsg.temlservice.services;

import ru.nabokovsg.temlservice.dto.protocol.NewProtocolTemplateDto;
import ru.nabokovsg.temlservice.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.temlservice.dto.template.NewTemplateDataDto;
import ru.nabokovsg.temlservice.models.PageTitleTemplate;
import ru.nabokovsg.temlservice.models.SubsectionTemplate;

public interface ProtocolTemplateService {

    ProtocolTemplateDto save(NewProtocolTemplateDto templateDto);

    void addPageTitleTemplate(NewTemplateDataDto template, PageTitleTemplate pageTitleTemplate);

    void addSubsectionTemplate(NewTemplateDataDto template,  SubsectionTemplate subsectionTemplate);
}