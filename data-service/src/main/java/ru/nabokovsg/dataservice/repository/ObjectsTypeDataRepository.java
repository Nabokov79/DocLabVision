package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.nabokovsg.dataservice.models.ObjectsType;
import ru.nabokovsg.dataservice.models.ObjectsTypeData;

import java.util.List;
import java.util.Set;

public interface ObjectsTypeDataRepository extends JpaRepository<ObjectsTypeData, Long> {

    @Query("select d from ObjectsTypeData d where d.objectsType in :objectsTypes")
    Set<ObjectsTypeData> findAllByObjectsType(@Param("objectsTypes") List<ObjectsType> objectsTypes);
}