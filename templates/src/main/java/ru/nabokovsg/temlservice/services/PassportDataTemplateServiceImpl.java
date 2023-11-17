package ru.nabokovsg.temlservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.client.TemplateClient;
import ru.nabokovsg.temlservice.dto.client.ObjectPassportDataTemplateDto;
import ru.nabokovsg.temlservice.mappers.PassportDataTemplateMapper;
import ru.nabokovsg.temlservice.models.PassportDataTemplate;
import ru.nabokovsg.temlservice.repository.PassportDataTemplateRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PassportDataTemplateServiceImpl implements PassportDataTemplateService {

    private final PassportDataTemplateRepository repository;
    private final PassportDataTemplateMapper mapper;
    private final TemplateClient client;

    @Override
    public List<PassportDataTemplate> save(Long objectsTypeId, List<PassportDataTemplate> passportData) {
        passportData.addAll(repository.saveAll(filter(client.getPassportData(objectsTypeId), passportData)));
        return passportData;
    }

    private List<PassportDataTemplate> filter(List<ObjectPassportDataTemplateDto> passportDataDto
            , List<PassportDataTemplate> passportsDataDb) {
        if (passportsDataDb.isEmpty()) {
            return passportDataDto.stream()
                                  .map(mapper::mapToPassportDataTemplate)
                                  .toList();
        }
        Set<String> names = passportsDataDb.stream()
                                           .map(PassportDataTemplate::getDataName)
                                           .collect(Collectors.toSet());
        passportsDataDb.addAll(passportDataDto.stream()
                                              .filter(p -> !names.contains(p.getDataName()))
                                              .map(mapper::mapToPassportDataTemplate)
                                              .toList());
        return passportsDataDb;

    }
}
