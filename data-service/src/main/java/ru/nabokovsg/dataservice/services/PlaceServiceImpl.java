package ru.nabokovsg.dataservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.place.NewPlaceDto;
import ru.nabokovsg.dataservice.dto.place.UpdatePlaceDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mappers.PlaceMapper;
import ru.nabokovsg.dataservice.models.Place;
import ru.nabokovsg.dataservice.repository.PlaceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository repository;
    private final PlaceMapper mapper;


    @Override
    public List<Place> save(List<NewPlaceDto> placesDto) {
        return getAllByPlaceName(mapper.mapToNewPlace(placesDto));
    }

    @Override
    public List<Place> update(List<UpdatePlaceDto> placesDto) {
        validateIds(placesDto.stream().map(UpdatePlaceDto::getId).toList());
        return getAllByPlaceName(mapper.mapToUpdatePlace(placesDto));
    }

    private void validateIds(List<Long> ids) {
        Map<Long, Place> places = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(Place::getId, d -> d));
        if (places.size() != ids.size() || places.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(places.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("places with ids= %s not found", ids));
        }
    }

    private List<Place> getAllByPlaceName(List<Place> places) {
        Map<String, Place> placesDb = repository.findAllByPlaceName(places.stream()
                                                                  .map(Place::getPlaceName)
                                                                  .distinct()
                                                                  .toList())
                                                                .stream()
                                                                .collect(Collectors.toMap(Place::getPlaceName, p -> p));
        if (placesDb.isEmpty()) {
            return repository.saveAll(places);
        } else {
            places = places.stream().filter(p -> !placesDb.containsKey(p.getPlaceName())).toList();
            if(!places.isEmpty()) {
                return repository.saveAll(places);
            }
            return placesDb.values().stream().toList();
        }
    }
}