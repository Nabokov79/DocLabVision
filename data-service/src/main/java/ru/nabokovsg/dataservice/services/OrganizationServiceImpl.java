package ru.nabokovsg.dataservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.organization.NewOrganizationDto;
import ru.nabokovsg.dataservice.dto.organization.OrganizationDto;
import ru.nabokovsg.dataservice.dto.organization.ShortOrganizationDto;
import ru.nabokovsg.dataservice.dto.organization.UpdateOrganizationDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mappers.OrganizationMapper;
import ru.nabokovsg.dataservice.models.Organization;
import ru.nabokovsg.dataservice.repository.OrganizationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository repository;
    private final OrganizationMapper mapper;
    private final RequisitesService requisitesService;

    @Override
    public OrganizationDto save(NewOrganizationDto organizationDto) {
        Organization organizationDb = repository.findByOrganization(organizationDto.getOrganization());
        if (organizationDb!= null) {
            return mapper.mapToOrganizationDto(organizationDb);
        }
        Organization organization = mapper.mapToNewOrganization(organizationDto);
        organization.setRequisites(requisitesService.save(organizationDto.getRequisites()));
        return mapper.mapToOrganizationDto(repository.save(organization));
    }

    @Override
    public OrganizationDto update(UpdateOrganizationDto organizationDto) {
        if (repository.existsById(organizationDto.getId())) {
            Organization organization = mapper.mapToUpdateOrganization(organizationDto);
            organization.setRequisites(requisitesService.update(organizationDto.getRequisites()));
            return mapper.mapToOrganizationDto(repository.save(mapper.mapToUpdateOrganization(organizationDto)));
        }
        throw new NotFoundException(
                String.format("organization=%s not found for update.", organizationDto.getOrganization()));
    }

    @Override
    public OrganizationDto get(Long id) {
        return mapper.mapToOrganizationDto(repository.findById(id).orElseThrow(() -> new NotFoundException(
                                                   String.format("Organization with id=%s not found for license",id))));
    }

    @Override
    public List<ShortOrganizationDto> getAll() {
        return mapper.mapToShortOrganizationDto(repository.findAllOrganization());
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
       throw new NotFoundException(String.format("Organization with id=%s not found for delete.", id));
    }
}