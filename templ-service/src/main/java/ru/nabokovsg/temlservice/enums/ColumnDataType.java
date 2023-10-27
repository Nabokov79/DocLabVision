package ru.nabokovsg.temlservice.enums;

import java.util.Optional;

public enum ColumnDataType {

    SEQUENCE_NUMBER,
    ELEMENT,
    SUB_ELEMENT,
    DEFECT,
    REPAIR,
    REFERENCE_POINT_NUMBER,
    REFERENCE_POINT_MEASURING,
    DEVIATION_YEAR,
    DEVIATION,
    PRECIPITATION,
    CONTROL_POINT_NUMBER,
    CONTROL_POINT_MEASURING,
    ADJACENT_CONTROL_POINTS,
    DIAMETRIC_CONTROL_POINT;

    public static Optional<ColumnDataType> from(String columnDataType) {
        for (ColumnDataType type : values()) {
            if (type.name().equalsIgnoreCase(columnDataType)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}