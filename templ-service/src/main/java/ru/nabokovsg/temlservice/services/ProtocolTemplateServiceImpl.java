package ru.nabokovsg.temlservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.dto.protocol.NewProtocolTemplateDto;
import ru.nabokovsg.temlservice.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.temlservice.mappers.ProtocolTemplateMapper;
import ru.nabokovsg.temlservice.repository.ProtocolTemplateRepository;

@Service
@RequiredArgsConstructor
public class ProtocolTemplateServiceImpl implements ProtocolTemplateService {

    private final ProtocolTemplateRepository repository;
    private final ProtocolTemplateMapper mapper;

    @Override
    public ProtocolTemplateDto save(NewProtocolTemplateDto templateDto) {
        if (repository.existsByObjectsTypeIdAndReportingDocumentId(templateDto.getTemplate().getObjectsTypeId()
                                                                , templateDto.getTemplate().getReportingDocumentId())) {
            return new ProtocolTemplateDto();
        }
        return mapper.mapToDocumentTemplateDto(repository.save(mapper.mapToNewProtocolTemplate(templateDto)));
    }
}