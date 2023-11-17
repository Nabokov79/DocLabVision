package ru.nabokovsg.temlservice.services.tables;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.dto.section.SectionTemplateDto;
import ru.nabokovsg.temlservice.dto.table.NewSubsectionTableTemplateDto;
import ru.nabokovsg.temlservice.mappers.SectionTemplateMapper;
import ru.nabokovsg.temlservice.mappers.TableTemplateMapper;
import ru.nabokovsg.temlservice.models.SectionTemplate;
import ru.nabokovsg.temlservice.models.SubsectionTemplate;
import ru.nabokovsg.temlservice.services.reports.SectionTemplateService;
import ru.nabokovsg.temlservice.services.subsections.SubsectionTemplateService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubsectionTableTemplateServiceImpl implements SubsectionTableTemplateService {

    private final TableTemplateService service;
    private final TableTemplateMapper mapper;
    private final SectionTemplateMapper sectionMapper;
    private final SectionTemplateService sectionService;
    private final SubsectionTemplateService subsectionService;

    @Override
    public SectionTemplateDto save(NewSubsectionTableTemplateDto templateDto) {
        SectionTemplate section = sectionService.get(templateDto.getSectionId());
        if (section != null) {
            Map<Long, SubsectionTemplate> subsections = section.getSubsections()
                    .stream()
                    .collect(Collectors.toMap(SubsectionTemplate::getId, s -> s));
            SubsectionTemplate subsection = subsections.get(templateDto.getSubsectionId());
            if (subsection.getTables().isEmpty()) {
                subsection.getTables().add(service.save(mapper.mapToNewSubsectionTableTemplate(templateDto)
                        , templateDto.getColumnHeaders()));
                for (SubsectionTemplate sub : subsectionService.saveAll(List.of(subsection))) {
                    subsections.put(subsection.getId(), sub);
                }
                section.setSubsections(section.getSubsections()
                        .stream()
                        .map(s -> {
                            if (s.getId() == subsection.getId()) {
                                s = subsection;
                            }
                            return s;
                        })
                        .toList());
            }
        }
        return sectionMapper.mapToSectionTemplateDto(section);
    }
}