package ru.nabokovsg.temlservice.models.enums;

import java.util.Optional;

public enum ColumnDataType {

    DATE,
    SURVEYS_TYPE,
    DESCRIPTION,
    DOCUMENT_NUMBER,
    ORGANIZATION,
    STRING_NUMBER,
    ELEMENT,
    DEFECT,
    REPAIR_PLACE;

    public static Optional<ColumnDataType> from(String columnDataType) {
        for (ColumnDataType type : values()) {
            if (type.name().equalsIgnoreCase(columnDataType)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}