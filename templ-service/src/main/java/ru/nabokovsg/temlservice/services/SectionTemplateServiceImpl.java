package ru.nabokovsg.temlservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.dto.report.ReportTemplateDto;
import ru.nabokovsg.temlservice.dto.section.NewReportSectionTemplateDto;
import ru.nabokovsg.temlservice.exceptions.NotFoundException;
import ru.nabokovsg.temlservice.mappers.SectionTemplateMapper;
import ru.nabokovsg.temlservice.models.ReportTemplate;
import ru.nabokovsg.temlservice.models.SectionTemplate;
import ru.nabokovsg.temlservice.repository.SectionTemplateRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SectionTemplateServiceImpl implements SectionTemplateService {

    private final SectionTemplateRepository repository;
    private final SectionTemplateMapper mapper;
    private final ReportTemplateService reportTemplateService;

    @Override
    public ReportTemplateDto save(NewReportSectionTemplateDto sectionTemplateDto) {
        ReportTemplate reportTemplate = reportTemplateService.getById(sectionTemplateDto.getObjectsTypeId()
                                                                    , sectionTemplateDto.getReportingDocumentId());
        if (reportTemplate != null) {
            List<SectionTemplate> sectionTemplates = repository.saveAll(sectionTemplateDto.getSectionTemplates()
                    .stream()
                    .map(mapper::mapToNewSectionTemplate)
                    .toList());
            reportTemplate.getSectionTemplates().addAll(sectionTemplates);
            return reportTemplateService.saveTemplate(reportTemplate);
        }
        return new ReportTemplateDto();
    }

    @Override
    public SectionTemplate getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Section template with id=%s not found", id)));
    }

    @Override
    public void saveTemplate(SectionTemplate sectionTemplate) {
        repository.save(sectionTemplate);
    }
}