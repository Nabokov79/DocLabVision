package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.nabokovsg.dataservice.models.PlotOfObject;

import java.util.List;
import java.util.Set;

public interface PlotOfObjectRepository extends JpaRepository<PlotOfObject, Long> {

    @Query("select p from PlotOfObject p where p.plotName in :plotNames")
    Set<PlotOfObject> findAllByPlotName(@Param("plotNames") List<String> plotNames);
}