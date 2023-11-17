package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.dataservice.models.Branch;
import ru.nabokovsg.dataservice.models.Department;

import java.util.Set;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Set<Department> findByBranch(Branch branch);

    Department findByDepartment(String department);
}