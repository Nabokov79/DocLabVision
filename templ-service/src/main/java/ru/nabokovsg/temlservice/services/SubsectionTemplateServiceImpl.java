package ru.nabokovsg.temlservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.dto.subsection.NewSubsectionTemplateDto;
import ru.nabokovsg.temlservice.dto.subsection.SubsectionTemplateDto;
import ru.nabokovsg.temlservice.enums.DocumentType;
import ru.nabokovsg.temlservice.enums.SubsectionDataType;
import ru.nabokovsg.temlservice.exceptions.BadRequestException;
import ru.nabokovsg.temlservice.mappers.SubsectionTemplateMapper;
import ru.nabokovsg.temlservice.models.SubsectionTemplate;
import ru.nabokovsg.temlservice.repository.SubsectionTemplateRepository;

@Service
@RequiredArgsConstructor
public class SubsectionTemplateServiceImpl implements SubsectionTemplateService {

    private final SubsectionTemplateRepository repository;
    private final SubsectionTemplateMapper mapper;

    @Override
    public SubsectionTemplateDto save(NewSubsectionTemplateDto templateDto) {
        SubsectionTemplate template = repository.save(mapper.mapToNewSubsectionTemplate(templateDto));
        template.setSubsectionDataType(SubsectionDataType.from(templateDto.getSubsectionDataType()).orElseThrow(
                () -> new BadRequestException(String.format("Unknown subsection data type=%s",
                        templateDto.getTemplate().getDocumentType()))));
        DocumentType type = DocumentType.from(templateDto.getTemplate().getDocumentType())
                .orElseThrow(
                        () -> new BadRequestException(String.format("Unknown document type=%s",
                                                            templateDto.getTemplate().getDocumentType())));
        return mapper.mapToSubsectionTemplateDto(template);
    }
}