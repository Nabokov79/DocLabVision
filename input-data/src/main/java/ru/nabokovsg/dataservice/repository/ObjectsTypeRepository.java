package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.nabokovsg.dataservice.models.ObjectsType;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

public interface ObjectsTypeRepository extends JpaRepository<ObjectsType, Long> {

    @Transactional
    @Query("select o from ObjectsType o where o in :objectsTypes")
    Set<ObjectsType> findAllByObjectsType(@Param("objectsTypes") List<ObjectsType> objectsTypes);
}