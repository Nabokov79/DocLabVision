package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.dataservice.models.Licenses;

public interface LicenseRepository extends JpaRepository<Licenses, Long> {

    Licenses findByLicenseNumber(String licenseNumber);
}