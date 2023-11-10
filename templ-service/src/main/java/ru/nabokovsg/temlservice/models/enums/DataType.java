package ru.nabokovsg.temlservice.models.enums;

import java.util.Optional;

public enum DataType {

    LICENSE,
    REQUISITES,
    SIGNATURE,
    ORGANIZATION,
    BRANCH,
    DEPARTMENT,
    ALL_DOCUMENT,
    REGULATORY_DOCUMENT,
    METHODOLOGICAL_DOCUMENT,
    DIVISION_DATA,
    TEXT,
    DOCUMENTATION;

    public static Optional<DataType> from(String dataType) {
        for (DataType type : values()) {
            if (type.name().equalsIgnoreCase(dataType)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}