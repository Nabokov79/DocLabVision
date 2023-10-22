package ru.nabokovsg.temlservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.dto.report.ReportTemplateDto;
import ru.nabokovsg.temlservice.dto.template.NewTemplateDataDto;
import ru.nabokovsg.temlservice.mappers.ReportTemplateMapper;
import ru.nabokovsg.temlservice.models.PageTitleTemplate;
import ru.nabokovsg.temlservice.models.ReportTemplate;
import ru.nabokovsg.temlservice.models.SectionTemplate;
import ru.nabokovsg.temlservice.repository.ReportTemplateRepository;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ReportTemplateServiceImpl implements ReportTemplateService {

    private final ReportTemplateRepository repository;
    private final ReportTemplateMapper mapper;

    @Override
    public ReportTemplateDto save(NewTemplateDataDto templateDto) {
        if (repository.existsByObjectsTypeIdAndReportingDocumentId(templateDto.getObjectsTypeId()
                                                                 , templateDto.getReportingDocumentId())) {
            return new ReportTemplateDto();
        }
        return mapper.mapToDocumentTemplateDto(repository.save(mapper.mapToNewReportTemplateDto(templateDto)));
    }

    @Override
    public void addPageTitleTemplate(NewTemplateDataDto template, PageTitleTemplate pageTitleTemplate) {
        ReportTemplate reportTemplate = repository.findByObjectsTypeIdAndReportingDocumentId(template.getObjectsTypeId()
                , template.getReportingDocumentId());
        if (reportTemplate != null) {
            reportTemplate.setTitlePageHeader(pageTitleTemplate);
        } else {
            save(template);
        }
    }

    @Override
    public void addSectionTemplate(List<Long> objectsTypeIds, List<Long> reportingDocumentIds, SectionTemplate sectionTemplate) {
        Set<ReportTemplate> reportTemplates = repository.findAllByObjectsTypeIdAndReportingDocumentId(objectsTypeIds, reportingDocumentIds);
        if (!reportTemplates.isEmpty()) {
            repository.saveAll(reportTemplates.stream()
                                              .peek(r -> {
                                                       List<SectionTemplate> sectionTemplates = r.getSectionTemplates();
                                                       sectionTemplates.add(sectionTemplate);
                                                         })
                                             .toList());
        }
    }
}