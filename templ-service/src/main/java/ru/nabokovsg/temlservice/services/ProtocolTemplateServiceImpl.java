package ru.nabokovsg.temlservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.dto.templates.DocumentTemplateDto;
import ru.nabokovsg.temlservice.dto.templates.NewTemplateDto;
import ru.nabokovsg.temlservice.mappers.ProtocolTemplateMapper;
import ru.nabokovsg.temlservice.models.ProtocolTemplate;
import ru.nabokovsg.temlservice.repository.ProtocolTemplateRepository;

@Service
@RequiredArgsConstructor
public class ProtocolTemplateServiceImpl implements ProtocolTemplateService {

    private final ProtocolTemplateRepository repository;
    private final ProtocolTemplateMapper mapper;
    private final PageTitleService pageTitleService;

    @Override
    public DocumentTemplateDto save(NewTemplateDto reportTemplateDto) {
        ProtocolTemplate template = mapper.mapToNewProtocolTemplateDto(reportTemplateDto);
        template.setLeftTitle(pageTitleService.save(reportTemplateDto.getPageTitleDto()));
        return mapper.mapToDocumentTemplateDto(repository.save(template));
    }
}