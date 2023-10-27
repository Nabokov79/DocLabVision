package ru.nabokovsg.temlservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.client.TemplateClient;
import ru.nabokovsg.temlservice.dto.pageTitle.NewPageTitleTemplateDto;
import ru.nabokovsg.temlservice.mappers.PageTitleTemplateMapper;
import ru.nabokovsg.temlservice.models.PageTitleTemplate;
import ru.nabokovsg.temlservice.repository.PageTitleTemplateRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PageTitleTemplateServiceImpl implements PageTitleTemplateService {

    private final PageTitleTemplateRepository repository;
    private final PageTitleTemplateMapper mapper;
    private final PageHeaderTemplateService pageHeaderTemplateService;
    private final TemplateClient client;
    private final StringBuilderService stringBuilderService;

    @Override
    public PageTitleTemplate save(Long reportingDocumentId, NewPageTitleTemplateDto pageTitleTemplateDto) {
        PageTitleTemplate pageTitleTemplate = mapper.mapToNewPageHeaderTemplate(pageTitleTemplateDto);
        pageTitleTemplate.setPageHeaderTemplate(pageHeaderTemplateService.save(reportingDocumentId
                                                                             , pageTitleTemplateDto.getPageHeader()));
        pageTitleTemplate.setEmployee(stringBuilderService.signatureEmployeeString(
                                                             client.getEmployee(pageTitleTemplateDto.getEmployeeId())));
        pageTitleTemplate.setYear(String.valueOf((LocalDate.now().getYear())));
        return repository.save(pageTitleTemplate);
    }
}
