package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.nabokovsg.dataservice.models.Element;
import ru.nabokovsg.dataservice.models.ObjectsType;

import java.util.List;
import java.util.Set;

public interface ElementRepository extends JpaRepository<Element, Long> {

    @Query("select e from Element e where e.objectsType in :objectsType")
    Set<Element> findAllByObjectsType(@Param("objectsType")List<ObjectsType> objectsType);
}