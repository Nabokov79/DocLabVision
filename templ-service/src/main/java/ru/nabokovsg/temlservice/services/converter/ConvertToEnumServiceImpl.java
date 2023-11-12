package ru.nabokovsg.temlservice.services.converter;

import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.models.enums.*;
import ru.nabokovsg.temlservice.exceptions.BadRequestException;

@Service
public class ConvertToEnumServiceImpl implements ConvertToEnumService {

    @Override
    public ColumnDataType covertColumnDataType(String columnDataType) {
        return ColumnDataType.from(columnDataType)
              .orElseThrow(() -> new BadRequestException(String.format("Unknown column data type=%s", columnDataType)));
    }

    @Override
    public DocumentType convertDocumentType(String documentType) {
        return DocumentType.from(documentType)
                .orElseThrow(() -> new BadRequestException(String.format("Unknown document type=%s", documentType)));
    }

    @Override
    public SubsectionDataType convertSubsectionDataType(String subsectionDataType) {
        return SubsectionDataType.from(subsectionDataType)
                .orElseThrow(() -> new BadRequestException(
                        String.format("Unknown subsection data type=%s",subsectionDataType))
                );
    }

    @Override
    public TableDataType convertTableDataType(String tableDataType) {
        return TableDataType.from(tableDataType)
                .orElseThrow(() -> new BadRequestException(String.format("Unknown table data type=%s", tableDataType)));
    }

    @Override
    public ProtocolType convertToProtocolType(String protocolType) {
        return ProtocolType.from(protocolType)
                .orElseThrow(() -> new BadRequestException(String.format("Unknown protocol type=%s", protocolType)));
    }

    @Override
    public DataType convertToDataType(String divisionType) {
        return DataType.from(divisionType)
                .orElseThrow(() -> new BadRequestException(String.format("Unknown division type=%s", divisionType)));
    }
}
