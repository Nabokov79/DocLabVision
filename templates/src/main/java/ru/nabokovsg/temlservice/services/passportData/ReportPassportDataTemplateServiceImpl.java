package ru.nabokovsg.temlservice.services.passportData;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.dto.passportData.NewReportPassportDataTemplateDto;
import ru.nabokovsg.temlservice.dto.section.SectionTemplateDto;
import ru.nabokovsg.temlservice.mappers.SectionTemplateMapper;
import ru.nabokovsg.temlservice.models.SectionTemplate;
import ru.nabokovsg.temlservice.services.PassportDataTemplateService;
import ru.nabokovsg.temlservice.services.reports.SectionTemplateService;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportPassportDataTemplateServiceImpl implements ReportPassportDataTemplateService {

    private final SectionTemplateService sectionService;
    private final SectionTemplateMapper sectionMapper;
    private final PassportDataTemplateService passportDataService;

    @Override
    public SectionTemplateDto save(NewReportPassportDataTemplateDto passportDataDto) {
        SectionTemplate section = sectionService.get(passportDataDto.getSectionId());
        if (section != null) {
            if (section.getPassportData().isEmpty()) {
                section.getPassportData().addAll(passportDataService.save(passportDataDto.getObjectsTypeId(), section.getPassportData()));
                return sectionMapper.mapToSectionTemplateDto(sectionService.saveSection(section));
            }
        }
        return sectionMapper.mapToSectionTemplateDto(section);
    }
}
