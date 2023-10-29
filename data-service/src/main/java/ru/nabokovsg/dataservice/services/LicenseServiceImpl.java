package ru.nabokovsg.dataservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.ObjectsIds;
import ru.nabokovsg.dataservice.dto.license.LicenseDto;
import ru.nabokovsg.dataservice.dto.license.NewLicenseDto;
import ru.nabokovsg.dataservice.dto.license.UpdateLicenseDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mappers.LicenseMapper;
import ru.nabokovsg.dataservice.enums.BuilderType;
import ru.nabokovsg.dataservice.models.DataBuilder;
import ru.nabokovsg.dataservice.models.Licenses;
import ru.nabokovsg.dataservice.repository.LicenseRepository;
import ru.nabokovsg.dataservice.services.builder.DataFactory;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LicenseServiceImpl implements LicenseService {

    private final LicenseRepository repository;
    private final LicenseMapper mapper;
    private final DataFactory factory;


    @Override
    public LicenseDto save(NewLicenseDto licenseDto) {
        return mapper.mapToLicenseDto(Objects.requireNonNullElseGet(
                repository.findByLicenseNumber(licenseDto.getLicenseNumber())
                                                 , () -> repository.save(set(mapper.mapToNewLicense(licenseDto)
                                                 , mapper.mapFromNewLicense(licenseDto)))));
    }

    @Override
    public LicenseDto update(UpdateLicenseDto licenseDto) {
        if (repository.existsById(licenseDto.getId())) {
            return mapper.mapToLicenseDto(repository.save(set(mapper.mapToUpdateLicense(licenseDto)
                    , mapper.mapFromUpdateLicense(licenseDto))));
        }
        throw new NotFoundException(String.format("license with id=%s not found for update", licenseDto.getId()));
    }

    @Override
    public LicenseDto get(Long id) {
        return mapper.mapToLicenseDto(repository.findById(id).orElseThrow(() -> new NotFoundException(
                                                                   String.format("license with id=%s not found", id))));
    }

    @Override
    public List<LicenseDto> getAll() {
        return mapper.mapToLicensesDto(repository.findAll());
    }

    private Licenses set(Licenses license, ObjectsIds ids) {
        DataBuilder builder = factory.getBuilder(List.of(ids), BuilderType.LICENSE);
        if (ids.getOrganizationId() != null) {
            license.setOrganization(builder.getOrganizations().get(ids.getOrganizationId()));
        }
        license.setIssuedLicense(builder.getOrganizations().get(ids.getIssuedLicenseId()));
        if(ids.getBranchId() != null) {
            license.setBranch(builder.getBranches().get(ids.getBranchId()));
        }
        if (ids.getDepartmentId() != null) {
            license.setDepartment(builder.getDepartments().get(ids.getDepartmentId()));
        }
        return license;
    }
}