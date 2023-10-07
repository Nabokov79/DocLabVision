package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.nabokovsg.dataservice.models.SubElement;

import java.util.List;
import java.util.Set;

public interface SubElementRepository extends JpaRepository<SubElement, Long> {

    @Query("select s from SubElement s where s.subElementName in :subElementNames")
    Set<SubElement> findAllBySubElementName(@Param("subElementNames") List<String> subElementNames);
}