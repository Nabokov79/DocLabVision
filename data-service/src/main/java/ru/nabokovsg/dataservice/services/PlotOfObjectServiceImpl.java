package ru.nabokovsg.dataservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.plotOfObject.NewPlotOfObjectDto;
import ru.nabokovsg.dataservice.dto.plotOfObject.UpdatePlotOfObjectDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mappers.PlotOfObjectMapper;
import ru.nabokovsg.dataservice.models.PlotOfObject;
import ru.nabokovsg.dataservice.repository.PlotOfObjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlotOfObjectServiceImpl implements PlotOfObjectService {

    private final PlotOfObjectRepository repository;
    private final PlotOfObjectMapper mapper;

    @Override
    public List<PlotOfObject> save(List<NewPlotOfObjectDto> plotOfObjectsDto) {
        plotOfObjectsDto = plotOfObjectsDto.stream().distinct().toList();
        Set<PlotOfObject> plot = repository.findAllByPlotName(plotOfObjectsDto.stream()
                                                                                .map(NewPlotOfObjectDto::getPlotName)
                                                                                .toList());
        if (plot == null || plot.isEmpty()) {
            return repository.saveAll(mapper.mapToNewPlotOfObject(plotOfObjectsDto));
        } else {
            Map<String, PlotOfObject> plotOfObjects = plot.stream()
                    .collect(Collectors.toMap(PlotOfObject::getPlotName, p -> p));
            plotOfObjectsDto = plotOfObjectsDto.stream().filter(p -> !plotOfObjects.containsKey(p.getPlotName())).toList();
            List<PlotOfObject> plotOfObjectsDb = repository.saveAll(mapper.mapToNewPlotOfObject(plotOfObjectsDto));
            plotOfObjectsDb.addAll(plotOfObjects.values());
            return plotOfObjectsDb;
        }
    }

    @Override
    public List<PlotOfObject> update(List<UpdatePlotOfObjectDto> plotOfObjectsDto) {
        validateIds(plotOfObjectsDto.stream().map(UpdatePlotOfObjectDto::getId).toList());
        return repository.saveAll(mapper.mapToUpdatePlotOfObject(plotOfObjectsDto));
    }

    private void validateIds(List<Long> ids) {
        Map<Long, PlotOfObject> plotOfObjects = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(PlotOfObject::getId, m -> m));
        if (plotOfObjects.size() != ids.size() || plotOfObjects.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(plotOfObjects.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("plot of object with ids= %s not found", ids));
        }
    }
}