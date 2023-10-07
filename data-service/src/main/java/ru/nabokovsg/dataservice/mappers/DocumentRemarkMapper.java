package ru.nabokovsg.dataservice.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.documentRemark.DocumentRemarkDto;
import ru.nabokovsg.dataservice.dto.documentRemark.NewDocumentRemarkDto;
import ru.nabokovsg.dataservice.dto.documentRemark.UpdateDocumentRemarkDto;
import ru.nabokovsg.dataservice.models.DocumentRemark;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DocumentRemarkMapper {

    DocumentRemark mapToNewDocumentRemark(NewDocumentRemarkDto remarkDto);

    DocumentRemark mapToUpdateDocumentRemark(UpdateDocumentRemarkDto remarkDto);

    DocumentRemarkDto mapToDocumentRemarkDto(DocumentRemark remark);

    List<DocumentRemarkDto> mapToAllDocumentRemarkDto(List<DocumentRemark> remarks);
}