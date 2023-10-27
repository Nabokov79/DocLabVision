package ru.nabokovsg.temlservice.services;

import ru.nabokovsg.temlservice.enums.*;

public interface ConvertStringToEnumService {

    ColumnDataType covertColumnDataType(String columnDataType);

    DocumentType convertDocumentType(String documentType);

    SubsectionDataType convertSubsectionDataType(String subsectionDataType);

    TableDataType convertTableDataType(String tableDataType);

    ProtocolType convertToProtocolType(String protocolType);
}