package ru.nabokovsg.dataservice.services;

import ru.nabokovsg.dataservice.dto.documentRemark.DocumentRemarkDto;
import ru.nabokovsg.dataservice.dto.documentRemark.NewDocumentRemarkDto;
import ru.nabokovsg.dataservice.dto.documentRemark.UpdateDocumentRemarkDto;

import java.util.List;

public interface DocumentRemarkService {

    DocumentRemarkDto save(NewDocumentRemarkDto remarkDto);

    DocumentRemarkDto update(UpdateDocumentRemarkDto remarkDto);

    List<DocumentRemarkDto> getAll(Long employeeId,Long employeeDocumentId, Long employeeDrawingId);

    void delete(Long id);
}