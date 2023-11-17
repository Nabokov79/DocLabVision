package ru.nabokovsg.dataservice.services;

import ru.nabokovsg.dataservice.dto.organization.NewOrganizationDto;
import ru.nabokovsg.dataservice.dto.organization.OrganizationDto;
import ru.nabokovsg.dataservice.dto.organization.ShortOrganizationDto;
import ru.nabokovsg.dataservice.dto.organization.UpdateOrganizationDto;
import ru.nabokovsg.dataservice.models.Licenses;
import ru.nabokovsg.dataservice.models.Organization;

import java.util.List;

public interface OrganizationService {

    OrganizationDto save(NewOrganizationDto organizationDto);

    OrganizationDto update(UpdateOrganizationDto organizationDto);

    OrganizationDto get(Long id);

    Organization getById(Long id);

    List<ShortOrganizationDto> getAll();

    void addLicense(Long id, Licenses license);

    void delete(Long id);
}