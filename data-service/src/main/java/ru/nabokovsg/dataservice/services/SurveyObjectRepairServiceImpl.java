package ru.nabokovsg.dataservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.surveyObjectRepair.NewSurveyObjectRepairDto;
import ru.nabokovsg.dataservice.dto.surveyObjectRepair.SurveyObjectRepairDto;
import ru.nabokovsg.dataservice.dto.surveyObjectRepair.UpdateSurveyObjectRepairDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mappers.SurveyObjectRepairMapper;
import ru.nabokovsg.dataservice.models.SurveyObjectPassport;
import ru.nabokovsg.dataservice.models.SurveyObjectRepair;
import ru.nabokovsg.dataservice.repository.SurveyObjectRepairRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SurveyObjectRepairServiceImpl implements SurveyObjectRepairService {

    private final SurveyObjectRepairRepository repository;
    private final SurveyObjectRepairMapper mapper;
    private final SurveyObjectPassportService passportService;

    @Override
    public List<SurveyObjectRepairDto> save(Long surveyObjectId, List<NewSurveyObjectRepairDto> repairsDto) {
        SurveyObjectPassport passport = getSurveyObjectPassport(surveyObjectId);
        return mapper.mapToRepairDto(
                repository.saveAll(mapper.mapFromNewRepair(repairsDto).stream()
                                                                      .peek(r -> r.setPassport(passport))
                                                                      .toList())
        );
    }

    @Override
    public List<SurveyObjectRepairDto> update(Long surveyObjectId, List<UpdateSurveyObjectRepairDto> repairsDto) {
        validateIds(repairsDto.stream().map(UpdateSurveyObjectRepairDto::getId).toList());
        SurveyObjectPassport passport = getSurveyObjectPassport(surveyObjectId);
        return mapper.mapToRepairDto(
                repository.saveAll(mapper.mapFromUpdateRepair(repairsDto).stream()
                        .peek(r -> r.setPassport(passport))
                        .toList())
        );
    }

    private void validateIds(List<Long> ids) {
        Map<Long, SurveyObjectRepair> repairs = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(SurveyObjectRepair::getId, m -> m));
        if (repairs.size() != ids.size() || repairs.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(repairs.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("Survey object repair with ids= %s not found", ids));
        }
    }

    private SurveyObjectPassport getSurveyObjectPassport(Long surveyObjectId) {
        return passportService.save(surveyObjectId);
    }
}