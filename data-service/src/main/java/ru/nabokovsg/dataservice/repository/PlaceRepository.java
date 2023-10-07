package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.nabokovsg.dataservice.models.Place;

import java.util.List;
import java.util.Set;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    @Query("select p from Place p where p.placeName in :placeNames")
    Set<Place> findAllByPlaceName(@Param("placeNames") List<String> placeNames);
}