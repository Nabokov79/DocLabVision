package ru.nabokovsg.temlservice.services.report;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.builders.TemplateData;
import ru.nabokovsg.temlservice.dto.report.ReportTemplateDto;
import ru.nabokovsg.temlservice.models.ReportTemplate;
import ru.nabokovsg.temlservice.models.SectionTemplate;
import ru.nabokovsg.temlservice.models.SubsectionTemplate;
import ru.nabokovsg.temlservice.models.TableTemplate;
import ru.nabokovsg.temlservice.services.SectionTemplateService;
import ru.nabokovsg.temlservice.services.subsection.SubsectionTemplateToReportService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportTemplateDataServiceImpl implements ReportTemplateDataService {

    private final ReportTemplateService reportService;
    private final SectionTemplateService sectionService;
    private final SubsectionTemplateToReportService subsectionService;

    @Override
    public ReportTemplateDto add(TemplateData data) {
        if (data.getReport() != null) {
//            switch (data.getType()) {
//                case SECTION -> {return saveSection(data.getReport(), data.getSectionTemplates());}
//                case SUBSECTION -> {return addSubsectionTemplate(data.getReport(), data.getSectionId(), data.getSubsectionTemplates());}
//                case TABLE -> {return addTableTemplate(data.getReport(), data.getSectionId(), data.getSequentialSubsectionNumber(), data.getTable());}
//            }
        }
        return new ReportTemplateDto();
    }


//    private ReportTemplateDto addTableTemplate(ReportTemplate report, Long sectionId, double sequentialSubsectionNumber, TableTemplate table) {
//        SectionTemplate section = report.getSectionTemplates()
//                                                .stream()
//                                                .collect(Collectors.toMap(SectionTemplate::getId, s -> s))
//                                                .get(sectionId);
//        section.setSubsectionsTemplates(subsectionService.save(section.getSubsectionsTemplates()
//                .stream()
//                .peek(s -> {
//                    if (sequentialSubsectionNumber == s.getSequentialSubsectionNumber()) {
//                        if (s.getTablesTemplate() == null) {
//                            s.setTablesTemplate(List.of(table));
//                        } else {
//                            s.getTablesTemplate().add(table);
//                        }
//                    }
//                })
//                .toList()));
//        report.setSectionTemplates(report.getSectionTemplates()
//                                                      .stream()
//                                                      .map(s -> {if (s.getId() == section.getId()) {
//                                                                      s = section;
//                                                                  }
//                                                                  return s;
//                                                         })
//                                                     .toList());
//        return reportService.saveReportTemplate(report);
//    }
}
