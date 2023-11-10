package ru.nabokovsg.temlservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.dto.report.ReportTemplateDto;
import ru.nabokovsg.temlservice.dto.section.NewReportSectionTemplateDto;
import ru.nabokovsg.temlservice.dto.section.NewSectionTemplateDto;
import ru.nabokovsg.temlservice.mappers.SectionTemplateMapper;
import ru.nabokovsg.temlservice.models.ReportTemplate;
import ru.nabokovsg.temlservice.models.SectionTemplate;
import ru.nabokovsg.temlservice.repository.SectionTemplateRepository;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SectionTemplateServiceImpl implements SectionTemplateService {

    private final SectionTemplateRepository repository;
    private final SectionTemplateMapper mapper;
    private final ReportTemplateService service;

    @Override
    public ReportTemplateDto save(NewReportSectionTemplateDto sectionTemplateDto) {
        ReportTemplate report = service.getById(sectionTemplateDto.getObjectsTypeId()
                                              , sectionTemplateDto.getReportingDocumentId());
        List<NewSectionTemplateDto> sections = sectionTemplateDto.getSections();
        if (report != null) {
            if (report.getSections() != null) {
                Set<String> sectionNames = report.getSections()
                                                               .stream()
                                                               .map(SectionTemplate::getSectionName)
                                                               .collect(Collectors.toSet());
                sections = sections.stream()
                                   .filter(s -> !sectionNames.contains(s.getSectionName()))
                                   .toList();
            }
            if (!sections.isEmpty()) {
                report.setSections(repository.saveAll(sections.stream()
                                                              .map(mapper::mapToNewSectionTemplate)
                                                              .toList()));
            }
        }
        return service.save(report);
    }

    @Override
    public SectionTemplate saveSection(SectionTemplate section) {
        return repository.save(section);
    }
}