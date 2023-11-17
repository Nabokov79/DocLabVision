package ru.nabokovsg.temlservice.services.subsections;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.dto.report.ReportTemplateDto;
import ru.nabokovsg.temlservice.dto.subsection.NewSectionSubsectionTemplate;
import ru.nabokovsg.temlservice.dto.subsection.NewSubsectionTemplateDto;
import ru.nabokovsg.temlservice.mappers.ReportTemplateMapper;
import ru.nabokovsg.temlservice.models.*;
import ru.nabokovsg.temlservice.repository.SubsectionTemplateRepository;
import ru.nabokovsg.temlservice.services.reports.ReportTemplateService;
import ru.nabokovsg.temlservice.services.reports.SectionTemplateService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubsectionTemplateServiceImpl implements SubsectionTemplateService {

    private final SubsectionTemplateRepository repository;
    private final ReportTemplateService reportService;
    private final ReportTemplateMapper reportMapper;
    private final SectionTemplateService sectionService;
    private final SubsectionDataTemplateService subsectionDataService;

    @Override
    public ReportTemplateDto save(NewSectionSubsectionTemplate subsectionsDto) {
        ReportTemplate report = reportService.getById( subsectionsDto.getObjectsTypeId()
                ,  subsectionsDto.getReportingDocumentId());
        if (report != null) {
            SectionTemplate sectionDb = report.getSections()
                    .stream()
                    .collect(Collectors.toMap(SectionTemplate::getId, s -> s))
                    .get(subsectionsDto.getSectionId());
            if (sectionDb != null) {
                Map<String, SubsectionTemplate> subsectionsDb = sectionDb.getSubsections()
                        .stream()
                        .collect(Collectors.toMap(SubsectionTemplate::getSubsectionName, s -> s));
                List<NewSubsectionTemplateDto> subsections = subsectionsDto.getSubsections().stream()
                        .filter(s -> !subsectionsDb.containsKey(s.getSubsectionName()))
                        .toList();
                if (!subsections.isEmpty()) {
                    sectionDb.getSubsections().addAll(repository.saveAll(
                            subsectionDataService.getSubsectionDataTemplate(subsectionsDto.getObjectsTypeId()
                                                                          , subsections))
                    );
                }
            }
            SectionTemplate section = sectionService.saveSection(sectionDb);
            report.setSections(report.getSections().stream()
                    .map(s -> {
                        if (s.getId() == section.getId()) {
                            s = section;
                        }
                        return s;
                    })
                    .toList());
        }
        return reportMapper.mapToReportTemplateDto(report);
    }

    @Override
    public List<SubsectionTemplate> saveAll(List<SubsectionTemplate> subsections) {
        return repository.saveAll(subsections);
    }
}