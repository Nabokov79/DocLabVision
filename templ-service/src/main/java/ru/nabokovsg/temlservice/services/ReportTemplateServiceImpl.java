package ru.nabokovsg.temlservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.client.TemplateClient;
import ru.nabokovsg.temlservice.dto.client.ReportingDocumentDto;
import ru.nabokovsg.temlservice.dto.report.NewReportTemplateDto;
import ru.nabokovsg.temlservice.dto.report.ReportTemplateDto;
import ru.nabokovsg.temlservice.exceptions.NotFoundException;
import ru.nabokovsg.temlservice.mappers.ReportTemplateMapper;
import ru.nabokovsg.temlservice.models.*;
import ru.nabokovsg.temlservice.repository.ReportTemplateRepository;

@Service
@RequiredArgsConstructor
public class ReportTemplateServiceImpl implements ReportTemplateService {

    private final ReportTemplateRepository repository;
    private final ReportTemplateMapper mapper;
    private final PageTitleTemplateService pageTitleTemplateService;
    private final TemplateClient client;

    @Override
    public ReportTemplateDto save(NewReportTemplateDto reportTemplateDto) {
        if (!repository.existsByObjectsTypeIdAndReportingDocumentId(reportTemplateDto.getObjectsTypeId()
                , reportTemplateDto.getReportingDocumentId())) {
            ReportingDocumentDto reportingDocument = client.getReportingDocument(reportTemplateDto.getReportingDocumentId());
            ReportTemplate template = mapper.mapToNewReportTemplateDto(reportTemplateDto);
            template.setReportName(reportingDocument.getDocument().toUpperCase());
            template.setReportTitle(reportingDocument.getDocumentTitle());
            template.setPageTitle(pageTitleTemplateService.save(reportTemplateDto.getReportingDocumentId(),reportTemplateDto.getPageTitleTemplate()));
            return mapper.mapToReportTemplateDto(repository.save(template));
        }
        return new ReportTemplateDto();
    }

    @Override
    public ReportTemplateDto get(Long id, Long objectsTypeId, Long reportingDocumentId) {
        if (objectsTypeId != null && reportingDocumentId != null) {
            return mapper.mapToReportTemplateDto(
                    repository.findByObjectsTypeIdAndReportingDocumentId(objectsTypeId, reportingDocumentId));
        }
        if (id != null) {
            return mapper.mapToReportTemplateDto(
                    repository.findById(id)
                            .orElseThrow(() -> new NotFoundException(
                                    String.format(" ReportTemplate with id=%s not found", id))));
        }
        return new ReportTemplateDto();
    }

    @Override
    public ReportTemplate getById(Long objectsTypeId, Long reportingDocumentId) {
        return repository.findByObjectsTypeIdAndReportingDocumentId(objectsTypeId, reportingDocumentId);
    }

    @Override
    public ReportTemplateDto saveTemplate(ReportTemplate reportTemplate) {
        return mapper.mapToReportTemplateDto(repository.save(reportTemplate));
    }
}