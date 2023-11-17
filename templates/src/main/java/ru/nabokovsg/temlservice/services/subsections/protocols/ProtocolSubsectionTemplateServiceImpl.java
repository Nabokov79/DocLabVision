package ru.nabokovsg.temlservice.services.subsections.protocols;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.temlservice.dto.subsection.NewProtocolSubsectionTemplate;
import ru.nabokovsg.temlservice.dto.subsection.NewSubsectionTemplateDto;
import ru.nabokovsg.temlservice.mappers.SubsectionTemplateMapper;
import ru.nabokovsg.temlservice.models.ProtocolTemplate;
import ru.nabokovsg.temlservice.models.SubsectionTemplate;
import ru.nabokovsg.temlservice.repository.SubsectionTemplateRepository;
import ru.nabokovsg.temlservice.services.ProtocolTemplateService;
import ru.nabokovsg.temlservice.services.subsections.SubsectionDataTemplateService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProtocolSubsectionTemplateServiceImpl implements ProtocolSubsectionTemplateService {

    private final SubsectionTemplateRepository repository;
    private final ProtocolTemplateService service;
    private final SubsectionTemplateMapper mapper;
    private final SubsectionDataTemplateService subsectionDataService;

    @Override
    public ProtocolTemplateDto save(NewProtocolSubsectionTemplate subsectionsDto) {
        ProtocolTemplate protocol = service.getById(subsectionsDto.getObjectsTypeId()
                                                  , subsectionsDto.getReportingDocumentId());
        if (protocol != null) {
            Map<String, SubsectionTemplate> subsectionsDb = protocol.getSubsections()
                    .stream()
                    .collect(Collectors.toMap(SubsectionTemplate::getSubsectionName, s -> s));
            List<NewSubsectionTemplateDto> subsections = subsectionsDto.getSubsections().stream()
                                                            .filter(s -> !subsectionsDb.containsKey(s.getSubsectionName()))
                                                            .toList();
            List<SubsectionTemplate> subsectionsD = subsectionDataService.getSubsectionDataTemplate(subsectionsDto.getObjectsTypeId(), subsections);
            protocol.getSubsections().addAll(repository.saveAll(subsectionsD));
        }
        return mapper.mapToProtocolTemplateDto(protocol);
    }
}