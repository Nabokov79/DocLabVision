package ru.nabokovsg.temlservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.mappers.ConclusionTemplateMapper;
import ru.nabokovsg.temlservice.repository.ConclusionTemplateRepository;

@Service
@RequiredArgsConstructor
public class ConclusionTemplateServiceImpl implements ConclusionTemplateService {

    private final ConclusionTemplateRepository repository;
    private final ConclusionTemplateMapper mapper;
}
