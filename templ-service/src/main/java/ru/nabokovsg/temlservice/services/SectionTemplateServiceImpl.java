package ru.nabokovsg.temlservice.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.dto.builders.IdsListBuilder;
import ru.nabokovsg.temlservice.dto.report.ReportTemplateDto;
import ru.nabokovsg.temlservice.dto.section.NewReportSectionTemplateDto;
import ru.nabokovsg.temlservice.mappers.SectionTemplateMapper;
import ru.nabokovsg.temlservice.models.ReportTemplate;
import ru.nabokovsg.temlservice.models.SectionTemplate;
import ru.nabokovsg.temlservice.models.SubsectionTemplate;
import ru.nabokovsg.temlservice.repository.SectionTemplateRepository;
import ru.nabokovsg.temlservice.dto.section.SectionTemplateDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
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
    public SectionTemplateDto addSubsectionTemplate(IdsListBuilder builder
                                                  , List<SubsectionTemplate> subsectionTemplate) {
        ReportTemplate reportTemplate = reportTemplateService.getById(builder.getObjectTypeId()
                                                                    , builder.getReportingDocumentId());
        if (reportTemplate != null) {
            Map<Long, SectionTemplate> sectionTemplates = reportTemplate.getSectionTemplates()
                                                            .stream()
                                                            .collect(Collectors.toMap(SectionTemplate::getId, s -> s));
            SectionTemplate sectionTemplate = sectionTemplates.get(builder.getSectionId());
            Map<String, SubsectionTemplate> subsectionsTemplatesDb =
                    sectionTemplate.getSubsectionsTemplates().stream()
                            .collect(Collectors.toMap(SubsectionTemplate::getSubsectionName, s -> s));
              if (sectionTemplate.getSubsectionsTemplates() != null) {
                  sectionTemplate.getSubsectionsTemplates().addAll(subsectionTemplate);
              } else {
                  List<SubsectionTemplate> subsectionTemplates = subsectionTemplate
                                  .stream()
                                  .filter(s -> !subsectionsTemplatesDb.containsKey(s.getSubsectionName()))
                                  .toList();
                  if (!subsectionTemplates.isEmpty()) {
                      sectionTemplate.setSubsectionsTemplates(subsectionTemplates);
                      return mapper.mapToSectionTemplateDto(repository.save(sectionTemplate));
                  }
              }
            return mapper.mapToSectionTemplateDto(sectionTemplate);
        }
        return new SectionTemplateDto();
    }
}