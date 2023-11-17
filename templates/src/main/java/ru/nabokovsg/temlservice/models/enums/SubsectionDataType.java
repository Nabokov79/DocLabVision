package ru.nabokovsg.temlservice.models.enums;

import java.util.Optional;

public enum SubsectionDataType {

    REGULATORY_DOCUMENT,
    METHODOLOGICAL_DOCUMENT,
    ALL_DOCUMENT,
    PASSPORT_DATA_OBJECT,
    LABORATORY_CERTIFICATION,
    EMPLOYEE_CERTIFICATION,
    LABORATORY_DATA_EMPLOYEE_CERTIFICATION,
    MEANS_CONTROL_MEASUREMENT,
    TEXT;

    public static Optional<SubsectionDataType> from(String subsectionDataType) {
        for (SubsectionDataType type : values()) {
            if (type.name().equalsIgnoreCase(subsectionDataType)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}