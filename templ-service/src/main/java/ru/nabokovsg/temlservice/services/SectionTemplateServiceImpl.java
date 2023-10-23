package ru.nabokovsg.temlservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.dto.section.SectionTemplateDto;
import ru.nabokovsg.temlservice.dto.section.NewSectionTemplateDto;
import ru.nabokovsg.temlservice.mappers.SectionTemplateMapper;
import ru.nabokovsg.temlservice.repository.SectionTemplateRepository;

@Service
@RequiredArgsConstructor
public class SectionTemplateServiceImpl implements SectionTemplateService {

    private final SectionTemplateRepository repository;
    private final SectionTemplateMapper mapper;

    @Override
    public SectionTemplateDto save(NewSectionTemplateDto templateDto) {
        return mapper.mapToSectionTemplateDto(repository.save(mapper.mapToNewSectionTemplate(templateDto)));
    }
}