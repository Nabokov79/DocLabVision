package ru.nabokovsg.dataservice.services;

import ru.nabokovsg.dataservice.dto.reportingDocumentData.DocumentDataDto;
import ru.nabokovsg.dataservice.dto.reportingDocumentData.DrawingDataDto;
import ru.nabokovsg.dataservice.dto.reportingDocumentData.ReportingDocumentDataDto;
import ru.nabokovsg.dataservice.dto.reportingDocumentData.UpdateReportingDocumentDataDto;
import ru.nabokovsg.dataservice.models.Application;
import ru.nabokovsg.dataservice.models.ReportingDocumentData;
import ru.nabokovsg.dataservice.dto.reportingDocumentData.ReportingDocumentDataSearchParametersDto;

import java.util.List;

public interface ReportingDocumentDataService {

    void save(List<Application> applications);

    List<ReportingDocumentDataDto> update(List<UpdateReportingDocumentDataDto> dataDto);

    List<ReportingDocumentDataDto> getAll(ReportingDocumentDataSearchParametersDto parameters);

    ReportingDocumentData getById(Long id);

    void saveDocumentData(DocumentDataDto pathDto);

    void saveDrawingData(DrawingDataDto pathDto);
}