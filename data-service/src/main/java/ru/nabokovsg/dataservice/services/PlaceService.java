package ru.nabokovsg.dataservice.services;

import org.springframework.validation.annotation.Validated;
import ru.nabokovsg.dataservice.dto.place.NewPlaceDto;
import ru.nabokovsg.dataservice.dto.place.UpdatePlaceDto;
import ru.nabokovsg.dataservice.models.Place;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface PlaceService {

    List<Place> save(@Valid List<NewPlaceDto> placesDto);

    List<Place> update(@Valid List<UpdatePlaceDto> placesDto);
}