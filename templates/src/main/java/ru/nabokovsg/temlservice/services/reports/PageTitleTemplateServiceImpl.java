package ru.nabokovsg.temlservice.services.reports;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.client.TemplateClient;
import ru.nabokovsg.temlservice.dto.client.EmployeeDto;
import ru.nabokovsg.temlservice.dto.client.ReportingDocumentDto;
import ru.nabokovsg.temlservice.dto.pageTitle.NewPageTitleTemplateDto;
import ru.nabokovsg.temlservice.mappers.PageTitleTemplateMapper;
import ru.nabokovsg.temlservice.dto.report.ReportTemplateDto;
import ru.nabokovsg.temlservice.models.ReportTemplate;
import ru.nabokovsg.temlservice.models.TemplateData;
import ru.nabokovsg.temlservice.models.enums.DataType;
import ru.nabokovsg.temlservice.repository.PageTitleTemplateRepository;
import ru.nabokovsg.temlservice.services.HeaderTemplateService;
import ru.nabokovsg.temlservice.services.converter.StringFactory;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PageTitleTemplateServiceImpl implements PageTitleTemplateService {

    private final PageTitleTemplateRepository repository;
    private final PageTitleTemplateMapper mapper;
    private final TemplateClient client;
    private final StringFactory converter;
    private final HeaderTemplateService headerService;
    private final ReportTemplateService reportService;

    @Override
    public ReportTemplateDto save(NewPageTitleTemplateDto pageTitleDto) {
        ReportTemplate report = reportService.getById(pageTitleDto.getObjectsTypeId()
                                                    , pageTitleDto.getReportingDocumentId());
        if (report == null) {
            ReportingDocumentDto document = client.getReportingDocument(pageTitleDto.getReportingDocumentId());
            EmployeeDto employee = client.getEmployee(pageTitleDto.getEmployeeId());
            return reportService.create(repository.save(mapper.mapToPageTitleTemplate(
                                                        mapper.mapToNewPageTitleTemplate(pageTitleDto)
                                                                , document.getDocument()
                                                                , document.getDocumentTitle()
                                                                , headerService.getHeader(pageTitleDto.getHeader())
                                                                , converter.create(
                                                                        new TemplateData.Builder()
                                                                                .type(DataType.SIGNATURE)
                                                                                .employee(employee)
                                                                                .build())
                                                                , String.valueOf((LocalDate.now().getYear()))))
                                                        , pageTitleDto);
        }
        return reportService.save(report);
    }
}