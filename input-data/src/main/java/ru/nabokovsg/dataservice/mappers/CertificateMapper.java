package ru.nabokovsg.dataservice.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.ObjectsIds;
import ru.nabokovsg.dataservice.dto.certificate.CertificateDto;
import ru.nabokovsg.dataservice.dto.certificate.NewCertificateDto;
import ru.nabokovsg.dataservice.dto.certificate.UpdateCertificateDto;
import ru.nabokovsg.dataservice.models.Certificate;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CertificateMapper {

    List<CertificateDto> mapToCertificatesDto(List<Certificate> certificates);

    Certificate mapToNewCertificate(NewCertificateDto newCertificateDto);

    Certificate mapToUpdateCertificate(UpdateCertificateDto updateCertificateDto);

    ObjectsIds mapFromNewCertificateDto(NewCertificateDto certificateDto);

    ObjectsIds mapFromUpdateCertificateDto(UpdateCertificateDto certificateDto);
}