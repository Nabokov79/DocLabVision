package ru.nabokovsg.temlservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.builders.TemplateData;
import ru.nabokovsg.temlservice.client.TemplateClient;
import ru.nabokovsg.temlservice.dto.pageTitle.NewPageTitleTemplateDto;
import ru.nabokovsg.temlservice.enums.DataType;
import ru.nabokovsg.temlservice.mappers.PageTitleTemplateMapper;
import ru.nabokovsg.temlservice.models.PageTitleTemplate;
import ru.nabokovsg.temlservice.repository.PageTitleTemplateRepository;
import ru.nabokovsg.temlservice.services.converters.ConvertObjectDataToStringService;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PageTitleTemplateServiceImpl implements PageTitleTemplateService {

    private final PageTitleTemplateRepository repository;
    private final PageTitleTemplateMapper mapper;
    private final PageHeaderTemplateService pageHeaderTemplateService;
    private final TemplateClient client;
    private final ConvertObjectDataToStringService stringBuilderService;

    @Override
    public PageTitleTemplate save(Long reportingDocumentId, NewPageTitleTemplateDto pageTitleTemplateDto) {
        PageTitleTemplate pageTitleTemplate = mapper.mapToNewPageHeaderTemplate(pageTitleTemplateDto);
        pageTitleTemplate.setPageHeaderTemplate(pageHeaderTemplateService.save(reportingDocumentId
                                                                             , pageTitleTemplateDto.getPageHeader()));
        pageTitleTemplate.setEmployee(stringBuilderService.createString(new TemplateData.Builder().dataType(DataType.SIGNATURE).employee(
                client.getEmployee(pageTitleTemplateDto.getEmployeeId())).build()));
        pageTitleTemplate.setYear(String.valueOf((LocalDate.now().getYear())));
        return repository.save(pageTitleTemplate);
    }
}
