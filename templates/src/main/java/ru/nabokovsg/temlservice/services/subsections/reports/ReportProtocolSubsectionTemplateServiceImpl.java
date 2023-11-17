package ru.nabokovsg.temlservice.services.subsections.reports;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.dto.reportProtocol.ReportProtocolTemplateDto;
import ru.nabokovsg.temlservice.dto.subsection.NewReportProtocolSubsectionTemplateDto;
import ru.nabokovsg.temlservice.mappers.ReportProtocolTemplateMapper;
import ru.nabokovsg.temlservice.models.ReportProtocolTemplate;
import ru.nabokovsg.temlservice.models.SubsectionTemplate;
import ru.nabokovsg.temlservice.repository.SubsectionTemplateRepository;
import ru.nabokovsg.temlservice.services.reports.ReportProtocolTemplateService;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportProtocolSubsectionTemplateServiceImpl implements ReportProtocolSubsectionTemplateService {

    private final SubsectionTemplateRepository repository;
    private final ReportProtocolTemplateService service;
    private final ReportProtocolTemplateMapper mapper;

    @Override
    public ReportProtocolTemplateDto save(NewReportProtocolSubsectionTemplateDto protocolDto) {
        ReportProtocolTemplate protocol = service.get(protocolDto.getProtocolId());
        if (protocol != null) {
            Map<Double, SubsectionTemplate> subsections = protocol.getSubsections()
                    .stream()
                    .collect(Collectors.toMap(SubsectionTemplate::getSequentialSubsectionNumber, s -> s));
            if (subsections.get(protocolDto.getSequentialSubsectionNumber()) == null) {
                protocol.getSubsections().add(
                        repository.save(mapper.mapToNewReportProtocolSubsectionTemplate(protocolDto))
                );
            }
        }
        return mapper.mapToReportProtocolTemplate(service.saveProtocolTemplate(protocol));
    }
}