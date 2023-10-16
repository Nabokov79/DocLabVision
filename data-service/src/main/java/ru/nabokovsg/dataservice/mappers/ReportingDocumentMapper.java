package ru.nabokovsg.dataservice.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.reportingDocument.NewReportingDocumentDto;
import ru.nabokovsg.dataservice.dto.reportingDocument.ReportingDocumentDto;
import ru.nabokovsg.dataservice.dto.reportingDocument.UpdateReportingDocumentDto;
import ru.nabokovsg.dataservice.models.ReportingDocument;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReportingDocumentMapper {

    ReportingDocument mapFromNewReportingDocument(NewReportingDocumentDto reportingDocumentDto);

    ReportingDocument mapFromUpdateReportingDocument(UpdateReportingDocumentDto reportingDocumentDto);

    List<ReportingDocumentDto> mapToReportingDocumentDto(List<ReportingDocument> reportingDocument);
}