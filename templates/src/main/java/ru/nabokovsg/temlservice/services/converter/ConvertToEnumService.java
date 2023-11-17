package ru.nabokovsg.temlservice.services.converter;

import ru.nabokovsg.temlservice.models.enums.*;

public interface ConvertToEnumService {

    ColumnDataType covertColumnDataType(String columnDataType);

    DocumentType convertDocumentType(String documentType);

    SubsectionDataType convertSubsectionDataType(String subsectionDataType);

    TableDataType convertTableDataType(String tableDataType);

    ProtocolType convertToProtocolType(String protocolType);

    DataType convertToDataType(String divisionType);
}