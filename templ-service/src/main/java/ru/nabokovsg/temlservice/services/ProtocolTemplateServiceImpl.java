package ru.nabokovsg.temlservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.dto.protocol.NewProtocolTemplateDto;
import ru.nabokovsg.temlservice.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.temlservice.dto.template.NewTemplateDataDto;
import ru.nabokovsg.temlservice.mappers.ProtocolTemplateMapper;
import ru.nabokovsg.temlservice.models.PageTitleTemplate;
import ru.nabokovsg.temlservice.models.ProtocolTemplate;
import ru.nabokovsg.temlservice.models.SubsectionTemplate;
import ru.nabokovsg.temlservice.repository.ProtocolTemplateRepository;

import java.util.List;

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

    @Override
    public void addPageTitleTemplate(NewTemplateDataDto template, PageTitleTemplate pageTitleTemplate) {
        ProtocolTemplate protocolTemplate = repository.findByObjectsTypeIdAndReportingDocumentId(
                                                                                     template.getObjectsTypeId()
                                                                                   , template.getReportingDocumentId());
        if (protocolTemplate != null) {
            protocolTemplate.setPageHeader(pageTitleTemplate);
            repository.save(protocolTemplate);
        }
    }

    @Override
    public void addSubsectionTemplate(NewTemplateDataDto template, SubsectionTemplate subsectionTemplate) {
        ProtocolTemplate protocolTemplate = repository.findByObjectsTypeIdAndReportingDocumentId(
                                                                                     template.getObjectsTypeId()
                                                                                   , template.getReportingDocumentId());
        if (protocolTemplate != null) {
            List<SubsectionTemplate> subsectionsTemplate = protocolTemplate.getSubsectionsTemplate();
            subsectionsTemplate.add(subsectionTemplate);
            repository.save(protocolTemplate);
        }
    }
}