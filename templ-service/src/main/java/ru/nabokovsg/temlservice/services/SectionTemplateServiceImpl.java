package ru.nabokovsg.temlservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.dto.section.SectionTemplateDto;
import ru.nabokovsg.temlservice.dto.section.NewSectionTemplateDto;
import ru.nabokovsg.temlservice.dto.template.NewTemplateDataDto;
import ru.nabokovsg.temlservice.mappers.SectionTemplateMapper;
import ru.nabokovsg.temlservice.models.ReportTemplate;
import ru.nabokovsg.temlservice.models.SectionTemplate;
import ru.nabokovsg.temlservice.models.SubsectionTemplate;
import ru.nabokovsg.temlservice.repository.SectionTemplateRepository;

@Service
@RequiredArgsConstructor
public class SectionTemplateServiceImpl implements SectionTemplateService {

    private final SectionTemplateRepository repository;
    private final SectionTemplateMapper mapper;
    private final ReportTemplateService reportTemplateService;

    @Override
    public SectionTemplateDto save(NewSectionTemplateDto templateDto) {
        SectionTemplate template = repository.save(mapper.mapToNewSectionTemplate(templateDto));
        reportTemplateService.addSectionTemplate(templateDto.getObjectsTypeIds()
                                               , templateDto.getReportingDocumentIds()
                                               , template);
        return mapper.mapToSectionTemplateDto(template);
    }

    @Override
    public void addSubsectionTemplate(NewTemplateDataDto template, SubsectionTemplate subsectionTemplate) {
        ReportTemplate reportTemplate = repository.findByObjectsTypeIdAndReportingDocumentId(template.getObjectsTypeId()
                , template.getReportingDocumentId());
        if (reportTemplate != null) {
            reportTemplate.setTitlePageHeader(pageTitleTemplate);
        } else {
            save(template);
        }
    }
}
