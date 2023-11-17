package ru.nabokovsg.dataservice.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.license.LicenseDto;
import ru.nabokovsg.dataservice.dto.license.NewLicenseDto;
import ru.nabokovsg.dataservice.dto.license.UpdateLicenseDto;
import ru.nabokovsg.dataservice.models.Licenses;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LicenseMapper {

    Licenses mapToNewLicense(NewLicenseDto licenseDto);

    LicenseDto mapToLicenseDto(Licenses license);

    Licenses mapToUpdateLicense(UpdateLicenseDto licenseDto);

    List<LicenseDto> mapToLicensesDto(List<Licenses> licenses);
}