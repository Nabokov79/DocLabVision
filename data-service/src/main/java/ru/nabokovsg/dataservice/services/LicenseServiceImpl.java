package ru.nabokovsg.dataservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.license.LicenseDto;
import ru.nabokovsg.dataservice.dto.license.NewLicenseDto;
import ru.nabokovsg.dataservice.dto.license.UpdateLicenseDto;
import ru.nabokovsg.dataservice.enums.DivisionType;
import ru.nabokovsg.dataservice.exceptions.BadRequestException;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mappers.LicenseMapper;
import ru.nabokovsg.dataservice.models.Licenses;
import ru.nabokovsg.dataservice.repository.LicenseRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LicenseServiceImpl implements LicenseService {

    private final LicenseRepository repository;
    private final LicenseMapper mapper;
    private final OrganizationService organizationService;
    private final BranchService branchService;
    private final DepartmentService departmentService;

    @Override
    public LicenseDto save(NewLicenseDto licenseDto) {
        Licenses license = mapper.mapToNewLicense(licenseDto);
        license.setIssuedLicense(organizationService.getById(licenseDto.getIssuedLicenseId()));
        try {
            license = repository.save(license);
        } catch (RuntimeException e) {
            return mapper.mapToLicenseDto(license);
        }
        DivisionType type = DivisionType.from(licenseDto.getDivisionType())
                .orElseThrow(() -> new BadRequestException(String.format("Unknown data format license=%s"
                                                                                    , licenseDto.getDivisionType())));
        switch (type) {
            case ORGANIZATION -> organizationService.addLicense(licenseDto.getDivisionId(), license);
            case BRANCH -> branchService.addLicense(licenseDto.getDivisionId(), license);
            case DEPARTMENT -> departmentService.addLicense(licenseDto.getDivisionId(), license);
            default ->  throw new BadRequestException(
                               String.format("Data format is not supported, license=%s", licenseDto.getDivisionType()));
        }
        return mapper.mapToLicenseDto(license);
    }

    @Override
    public LicenseDto update(UpdateLicenseDto licenseDto) {
        if (repository.existsById(licenseDto.getId())) {
            return mapper.mapToLicenseDto(repository.save(mapper.mapToUpdateLicense(licenseDto)));
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
}