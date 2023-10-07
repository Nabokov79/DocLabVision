package ru.nabokovsg.dataservice.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.reportingDocumentData.ReportingDocumentDataDto;
import ru.nabokovsg.dataservice.dto.reportingDocumentData.UpdateReportingDocumentDataDto;
import ru.nabokovsg.dataservice.models.Application;
import ru.nabokovsg.dataservice.models.ReportingDocumentData;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReportingDocumentDataMapper {

    ReportingDocumentData mapToReportingDocumentData(Application application);

   ReportingDocumentData mapToUpdateReportingDocumentData(UpdateReportingDocumentDataDto dataDto);

    List<ReportingDocumentDataDto> mapToReportingDocumentDataDto(List<ReportingDocumentData> data);
}