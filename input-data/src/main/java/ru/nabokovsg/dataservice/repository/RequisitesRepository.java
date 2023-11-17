package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.dataservice.models.Requisites;

public interface RequisitesRepository extends JpaRepository<Requisites, Long> {
}
