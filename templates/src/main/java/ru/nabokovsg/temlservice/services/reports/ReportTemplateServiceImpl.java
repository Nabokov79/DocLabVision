package ru.nabokovsg.temlservice.services.reports;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.dto.pageTitle.NewPageTitleTemplateDto;
import ru.nabokovsg.temlservice.dto.report.ReportTemplateDto;
import ru.nabokovsg.temlservice.exceptions.NotFoundException;
import ru.nabokovsg.temlservice.mappers.ReportTemplateMapper;
import ru.nabokovsg.temlservice.models.PageTitleTemplate;
import ru.nabokovsg.temlservice.models.ReportTemplate;
import ru.nabokovsg.temlservice.repository.ReportTemplateRepository;

@Service
@RequiredArgsConstructor
public class ReportTemplateServiceImpl implements ReportTemplateService {

    private final ReportTemplateRepository repository;
    private final ReportTemplateMapper mapper;

    @Override
    public ReportTemplateDto save(ReportTemplate report) {
        if (report == null) {
            return new ReportTemplateDto();
        }
        return mapper.mapToReportTemplateDto(repository.save(report));
    }

    @Override
    public ReportTemplateDto get(Long objectsTypeId, Long reportingDocumentId) {
        ReportTemplate report = getById(objectsTypeId, reportingDocumentId);
        if (report == null) {
            throw new NotFoundException(
                    String.format("ReportTemplate by objectsTypeId=%s, reportingDocumentId=%s not found"
                                                                                        , objectsTypeId
                                                                                        , reportingDocumentId));
        }
        return mapper.mapToReportTemplateDto(report);
    }

    @Override
    public ReportTemplate getById(Long objectsTypeId, Long reportingDocumentId) {
        return repository.findByObjectsTypeIdAndReportingDocumentId(objectsTypeId, reportingDocumentId);
    }

    @Override
    public ReportTemplateDto create(PageTitleTemplate pageTitle, NewPageTitleTemplateDto pageTitleDto) {
        return save(mapper.mapToNewReportTemplate(pageTitle
                                                , pageTitleDto.getObjectsTypeId()
                                                , pageTitleDto.getReportingDocumentId()));
    }
}