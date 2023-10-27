package ru.nabokovsg.temlservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.mappers.RecommendationTemplateMapper;
import ru.nabokovsg.temlservice.repository.RecommendationTemplateRepository;

@Service
@RequiredArgsConstructor
public class RecommendationTemplateServiceImpl implements RecommendationTemplateService {

    private final RecommendationTemplateRepository repository;
    private final RecommendationTemplateMapper mapper;
}
