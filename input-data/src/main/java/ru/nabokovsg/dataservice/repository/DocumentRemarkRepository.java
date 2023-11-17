package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.dataservice.models.DocumentRemark;

public interface DocumentRemarkRepository extends JpaRepository<DocumentRemark, Long> {
}
