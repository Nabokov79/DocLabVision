package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.dataservice.models.Building;
import ru.nabokovsg.dataservice.models.Department;

import java.util.Set;

public interface BuildingRepository extends JpaRepository<Building, Long> {

    Set<Building> findAllByDepartment(Department department);
}