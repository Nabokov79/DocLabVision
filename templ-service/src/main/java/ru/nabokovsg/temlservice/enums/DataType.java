package ru.nabokovsg.temlservice.enums;

import java.util.Optional;

public enum DataType {

    LICENSE,
    REQUISITES,
    SHORT_REQUISITES,
    ADDRESS,
    SIGNATURE,
    DOCUMENTATION,
    ORGANIZATION,
    BRANCH,
    DEPARTMENT,
    SECTION,
    SUBSECTION,
    TABLE;
    public static Optional<DataType> from(String divisionType) {
        for (DataType type : values()) {
            if (type.name().equalsIgnoreCase(divisionType)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}
