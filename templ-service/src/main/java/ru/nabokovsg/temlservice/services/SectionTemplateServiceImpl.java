package ru.nabokovsg.temlservice.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.dto.report.ReportTemplateDto;
import ru.nabokovsg.temlservice.dto.section.NewReportSectionTemplateDto;
import ru.nabokovsg.temlservice.exceptions.NotFoundException;
import ru.nabokovsg.temlservice.mappers.ReportTemplateMapper;
import ru.nabokovsg.temlservice.mappers.SectionTemplateMapper;
import ru.nabokovsg.temlservice.models.ReportTemplate;
import ru.nabokovsg.temlservice.models.SectionTemplate;
import ru.nabokovsg.temlservice.repository.SectionTemplateRepository;
import ru.nabokovsg.temlservice.services.report.ReportTemplateService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SectionTemplateServiceImpl implements SectionTemplateService {

    private final SectionTemplateRepository repository;
    private final SectionTemplateMapper mapper;
    private final ReportTemplateService service;
    private final ReportTemplateMapper reportTemplateMapper;

    @Override
    public ReportTemplateDto save(NewReportSectionTemplateDto sectionTemplateDto) {
        ReportTemplate report = service.getById(sectionTemplateDto.getObjectsTypeId()
                                              , sectionTemplateDto.getReportingDocumentId());
        if (report != null) {
            if (report.getSectionTemplates() != null) {
                List<Integer> sequentialSectionNumbers = report.getSectionTemplates()
                                                              .stream()
                                                              .map(SectionTemplate::getSequentialSectionNumber)
                                                              .toList();
               List<SectionTemplate> sections = sectionTemplateDto.getSections()
                                                                  .stream()
                                                                  .filter(s -> !sequentialSectionNumbers.contains(s.getSequentialSectionNumber()))
                                                                  .map(mapper::mapToNewSectionTemplate)
                                                                  .toList();
                if (!sections.isEmpty()) {
                    report.getSectionTemplates().addAll(saveAll(sections));
                } else {
                    return reportTemplateMapper.mapToReportTemplateDto(report);
                }
            } else {
                report.setSectionTemplates(saveAll(sectionTemplateDto.getSections()
                                                                                .stream()
                                                                                .map(mapper::mapToNewSectionTemplate)
                                                                                .toList()));
            }
            return service.saveReportTemplate(report);
        }
       throw new NotFoundException(
               String.format("Report template by objectsTypeId=%s, reportingDocumentId=%s not found"
                                                                       , sectionTemplateDto.getObjectsTypeId()
                                                                       , sectionTemplateDto.getReportingDocumentId()));
    }

    @Override
    public List<SectionTemplate> saveAll(List<SectionTemplate> sections) {
        return repository.saveAll(sections);
    }

    @Override
    public SectionTemplate updateSectionTemplate(SectionTemplate section) {
        return repository.save(section);
    }
}