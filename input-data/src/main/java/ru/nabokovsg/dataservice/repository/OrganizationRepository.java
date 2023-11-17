package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.dataservice.models.Organization;

import java.util.Set;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    Organization findByOrganization(String organization);

    @Query("select o.organization, o.shortNameOrganization from Organization o")
    Set<Organization> findAllOrganization();
}