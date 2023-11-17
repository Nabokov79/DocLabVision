package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.dataservice.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}