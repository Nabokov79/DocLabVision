package ru.nabokovsg.temlservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.dto.report.ReportTemplateDto;
import ru.nabokovsg.temlservice.dto.template.NewTemplateDataDto;
import ru.nabokovsg.temlservice.mappers.ReportTemplateMapper;
import ru.nabokovsg.temlservice.repository.ReportTemplateRepository;

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
}