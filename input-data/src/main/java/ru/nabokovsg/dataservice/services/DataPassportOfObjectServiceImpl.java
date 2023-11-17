package ru.nabokovsg.dataservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.dataPassportOfObject.DataPassportOfObjectDto;
import ru.nabokovsg.dataservice.dto.dataPassportOfObject.NewDataPassportOfObjectDto;
import ru.nabokovsg.dataservice.dto.dataPassportOfObject.UpdateDataPassportOfObjectDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mappers.DataPassportOfObjectMapper;
import ru.nabokovsg.dataservice.models.*;
import ru.nabokovsg.dataservice.repository.DataPassportOfObjectRepository;
import ru.nabokovsg.dataservice.services.builder.RepositoryRequestService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DataPassportOfObjectServiceImpl implements DataPassportOfObjectService {

    private final DataPassportOfObjectRepository repository;
    private final DataPassportOfObjectMapper mapper;
    private final SurveyObjectPassportService passportService;
    private final RepositoryRequestService requestService;

    @Override
    public List<DataPassportOfObjectDto> save(Long surveyObjectId, List<NewDataPassportOfObjectDto> dataDto) {
        SurveyObjectPassport passport = getSurveyObjectPassport(surveyObjectId);
        Map<Long, ObjectPassportDataTemplate> templates = getObjectPassportDataTemplate(dataDto
                                                                         .stream()
                                                                         .map(NewDataPassportOfObjectDto::getTemplateId)
                                                                         .toList());
        List<DataPassportOfObject> data = new ArrayList<>();
        for (NewDataPassportOfObjectDto dataPassportDto : dataDto) {
            DataPassportOfObject dataPassport = mapper.mapToNewDataPassportOfObject(dataPassportDto);
            dataPassport.setTemplate(templates.get(dataPassportDto.getTemplateId()));
            dataPassport.setPassport(passport);
            data.add(dataPassport);
        }
        return mapper.mapToDataPassportOfObjectDto(repository.saveAll(data));
    }

    @Override
    public List<DataPassportOfObjectDto> update(Long surveyObjectId, List<UpdateDataPassportOfObjectDto> dataDto) {
        validateIds(dataDto.stream().map(UpdateDataPassportOfObjectDto::getId).toList());
        SurveyObjectPassport passport = getSurveyObjectPassport(surveyObjectId);
        Map<Long, ObjectPassportDataTemplate> templates = getObjectPassportDataTemplate(dataDto
                                                                      .stream()
                                                                      .map(UpdateDataPassportOfObjectDto::getTemplateId)
                                                                      .toList());
        List<DataPassportOfObject> data = new ArrayList<>();
        for (UpdateDataPassportOfObjectDto dataPassportDto : dataDto) {
            DataPassportOfObject dataPassport = mapper.mapToUpdateDataPassportOfObject(dataPassportDto);
            dataPassport.setTemplate(templates.get(dataPassportDto.getTemplateId()));
            dataPassport.setPassport(passport);
            data.add(dataPassport);
        }
        return mapper.mapToDataPassportOfObjectDto(repository.saveAll(data));
    }

    private void validateIds(List<Long> ids) {
        Map<Long, DataPassportOfObject> data = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(DataPassportOfObject::getId, m -> m));
        if (data.size() != ids.size() || data.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(data.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("DataPassportOfObject with ids= %s not found", ids));
        }
    }

    private SurveyObjectPassport getSurveyObjectPassport(Long surveyObjectId) {
        return passportService.save(surveyObjectId);
    }

    private Map<Long, ObjectPassportDataTemplate> getObjectPassportDataTemplate(List<Long> templateIds) {
        return requestService.getTemplates(templateIds).stream()
                                                  .collect(Collectors.toMap(ObjectPassportDataTemplate::getId, t -> t));
    }
}