package ru.nabokovsg.temlservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.dto.templates.DocumentTemplateDto;
import ru.nabokovsg.temlservice.dto.templates.NewTemplateDto;
import ru.nabokovsg.temlservice.models.ReportTemplate;
import ru.nabokovsg.temlservice.mappers.ReportTemplateMapper;
import ru.nabokovsg.temlservice.repository.ReportTemplateRepository;

@Service
@RequiredArgsConstructor
public class ReportTemplateServiceImpl implements ReportTemplateService {

    private final ReportTemplateRepository repository;
    private final ReportTemplateMapper mapper;
    private final PageTitleService pageTitleService;

    @Override
    public DocumentTemplateDto save(NewTemplateDto reportTemplateDto) {
        ReportTemplate template = mapper.mapToNewReportTemplateDto(reportTemplateDto);
        template.setPageTitle(pageTitleService.save(reportTemplateDto.getPageTitleDto()));
        return mapper.mapToDocumentTemplateDto(repository.save(template));
    }
}