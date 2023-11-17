package ru.nabokovsg.temlservice.services.reports;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.client.TemplateClient;
import ru.nabokovsg.temlservice.dto.client.ReportingDocumentDto;
import ru.nabokovsg.temlservice.dto.reportProtocol.NewReportProtocolTemplateDto;
import ru.nabokovsg.temlservice.dto.section.SectionTemplateDto;
import ru.nabokovsg.temlservice.exceptions.NotFoundException;
import ru.nabokovsg.temlservice.mappers.ReportProtocolTemplateMapper;
import ru.nabokovsg.temlservice.mappers.SectionTemplateMapper;
import ru.nabokovsg.temlservice.models.ReportProtocolTemplate;
import ru.nabokovsg.temlservice.models.SectionTemplate;
import ru.nabokovsg.temlservice.repository.ReportProtocolTemplateRepository;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportProtocolTemplateServiceImpl implements ReportProtocolTemplateService {

    private final ReportProtocolTemplateRepository repository;
    private final ReportProtocolTemplateMapper mapper;
    private final SectionTemplateService sectionService;

    private final SectionTemplateMapper sectionMapper;
    private final TemplateClient client;

    @Override
    public SectionTemplateDto save(NewReportProtocolTemplateDto protocolDto) {
        SectionTemplate section = sectionService.get(protocolDto.getSectionId());
        if (section != null) {
            Map<String, ReportProtocolTemplate> protocols = section.getProtocols()
                                            .stream()
                                            .collect(Collectors.toMap(ReportProtocolTemplate::getProtocolTitle, p -> p));
            ReportingDocumentDto documentDto = client.getReportingDocument(protocolDto.getReportingDocumentId());
            if (protocols.get(documentDto.getDocumentTitle()) == null) {
                section.getProtocols().add(repository.save(mapper.mapToNewReportProtocolTemplate(protocolDto
                                                                                               , documentDto)));
            }
        }
        return sectionMapper.mapToSectionTemplateDto(sectionService.saveSection(section));
    }

    @Override
    public ReportProtocolTemplate saveProtocolTemplate(ReportProtocolTemplate protocol) {
        return repository.save(protocol);
    }

    @Override
    public ReportProtocolTemplate get(Long id) {
        return repository.findById(id)
          .orElseThrow(() -> new NotFoundException(String.format("Report protocol template with id=%s not found", id)));
    }
}