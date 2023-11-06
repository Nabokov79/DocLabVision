package ru.nabokovsg.temlservice.services.subsection;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.temlservice.dto.subsection.NewSubsectionTemplateToProtocolDto;
import ru.nabokovsg.temlservice.exceptions.NotFoundException;
import ru.nabokovsg.temlservice.mappers.SubsectionTemplateMapper;
import ru.nabokovsg.temlservice.models.ProtocolTemplate;
import ru.nabokovsg.temlservice.models.SubsectionTemplate;
import ru.nabokovsg.temlservice.repository.SubsectionTemplateRepository;
import ru.nabokovsg.temlservice.services.ProtocolTemplateService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubsectionTemplateToProtocolServiceImpl implements SubsectionTemplateToProtocolService {

    private final ProtocolTemplateService service;
    private final SubsectionTemplateMapper mapper;
    private final SubsectionTemplateRepository repository;

    @Override
    public ProtocolTemplateDto save(NewSubsectionTemplateToProtocolDto templateDto) {
        ProtocolTemplate protocol = service.getById(templateDto.getObjectsTypeId(), templateDto.getReportingDocumentId());
        if (protocol != null) {
            List<SubsectionTemplate> subsections = protocol.getSubsectionsTemplate();
            if (subsections.isEmpty()) {
                protocol.setSubsectionsTemplate(repository.saveAll(mapper.mapToNewSubsectionTemplateToProtocol(templateDto.getSubsectionTemplates())));
            } else {
                List<String> subsectionNames = subsections.stream().map(SubsectionTemplate::getSubsectionName).toList();
                protocol.setSubsectionsTemplate(repository.saveAll(mapper.mapToNewSubsectionTemplateToProtocol(templateDto.getSubsectionTemplates()
                        .stream()
                        .filter(s -> !subsectionNames.contains(s.getSubsectionName()))
                        .toList())));
            }
            return service.saveProtocolTemplate(protocol);
        }
        throw new NotFoundException(String.format("Protocol template by objectsTypeId=%s, reportingDocumentId=%s"
                                                                              , templateDto.getObjectsTypeId()
                                                                              , templateDto.getReportingDocumentId()));
    }
}
