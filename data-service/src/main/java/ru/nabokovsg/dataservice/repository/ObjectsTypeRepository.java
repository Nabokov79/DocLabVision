package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.dataservice.models.ObjectsType;

public interface ObjectsTypeRepository extends JpaRepository<ObjectsType, Long> {
}