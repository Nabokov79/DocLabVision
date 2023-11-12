package ru.nabokovsg.temlservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.nabokovsg.temlservice.models.ColumnHeaderTemplate;

import java.util.List;
import java.util.Set;

public interface ColumnHeaderTemplateRepository extends JpaRepository<ColumnHeaderTemplate, Long> {

    @Query("select c from ColumnHeaderTemplate c where c.cellName in :cellNames")
    Set<ColumnHeaderTemplate> findAllByCellName(@Param("cellNames") List<String> cellNames);
}