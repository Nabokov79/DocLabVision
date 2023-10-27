package ru.nabokovsg.temlservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.mappers.AppendicesTemplateMapper;
import ru.nabokovsg.temlservice.repository.AppendicesTemplateRepository;

@Service
@RequiredArgsConstructor
public class AppendicesTemplateServiceImpl implements AppendicesTemplateService {

    private final AppendicesTemplateRepository repository;
    private final AppendicesTemplateMapper mapper;
}
