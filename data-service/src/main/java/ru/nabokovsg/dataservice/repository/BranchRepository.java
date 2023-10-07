package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.dataservice.models.Branch;
import ru.nabokovsg.dataservice.models.Organization;

import java.util.Set;

public interface BranchRepository extends JpaRepository<Branch, Long> {

    Set<Branch> findAllByOrganization(Organization organization);
}