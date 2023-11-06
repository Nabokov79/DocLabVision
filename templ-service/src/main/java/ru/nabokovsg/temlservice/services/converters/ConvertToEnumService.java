package ru.nabokovsg.temlservice.services.converters;

import ru.nabokovsg.temlservice.enums.*;

public interface ConvertToEnumService {

    ColumnDataType covertColumnDataType(String columnDataType);

    DocumentType convertDocumentType(String documentType);

    SubsectionDataType convertSubsectionDataType(String subsectionDataType);

    TableDataType convertTableDataType(String tableDataType);

    ProtocolType convertToProtocolType(String protocolType);

    DataType convertToDataType(String divisionType);

    DivisionType convertToDivisionType(String divisionType);
}