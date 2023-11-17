package ru.nabokovsg.temlservice.models.enums;

import java.util.Optional;

public enum TableDataType {

    REFERENCE_POINT_MEASURING,
    CONTROL_POINT_MEASURING,
    VISUAL_MEASURING_CONTROL,
    REPAIRS_DATA,
    SURVEYS_DATA;

    public static Optional<TableDataType> from(String tableDataType) {
        for (TableDataType type : values()) {
            if (type.name().equalsIgnoreCase(tableDataType)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}