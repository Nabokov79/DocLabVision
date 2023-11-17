package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.dataservice.models.Norm;

public interface NormsRepository extends JpaRepository<Norm, Long> {
}