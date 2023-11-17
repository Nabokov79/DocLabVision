package ru.nabokovsg.dataservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.surveyObjectPassport.SurveyObjectPassportDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mappers.SurveyObjectPassportMapper;
import ru.nabokovsg.dataservice.models.SurveyObjectPassport;
import ru.nabokovsg.dataservice.models.SurveyObject;
import ru.nabokovsg.dataservice.repository.SurveyObjectPassportRepository;
import ru.nabokovsg.dataservice.services.builder.RepositoryRequestService;

@Service
@RequiredArgsConstructor
public class SurveyObjectPassportServiceImpl implements SurveyObjectPassportService {

    private final SurveyObjectPassportRepository repository;
    private final SurveyObjectPassportMapper mapper;
    private final RepositoryRequestService requestService;

    @Override
    public SurveyObjectPassport save(Long surveyObjectId) {
        SurveyObject object = requestService.getSurveyObject(surveyObjectId);
        SurveyObjectPassport passport = repository.findByObject(object);
        if (passport != null) {
            return passport;
        } else {
            passport = new SurveyObjectPassport();
            passport.setObject(object);
        }
        return repository.save(passport);
    }

    @Override
    public SurveyObjectPassportDto get(Long id) {
        return mapper.mapToPassportDto(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Passport object with id=%s not found", id))));
    }
}