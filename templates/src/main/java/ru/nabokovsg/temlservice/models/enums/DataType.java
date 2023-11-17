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
    NO_DATA,
    DOCUMENTATION,
    LABORATORY_DATA,
    MEANS_CONTROL_MEASUREMENT,
    CONCLUSION,
    VISUAL_MEASURING_CONTROL,
    ULTRASONIC_MEASURING_CONTROL,
    GEODESIC_MEASURING,
    ULTRASONIC_CONTROL,
    HARDNESS_MEASURING,
    REFERENCE_POINT_MEASURING,
    CONTROL_POINT_MEASURING,
    PASSPORT_DATA;

    public static Optional<DataType> from(String dataType) {
        for (DataType type : values()) {
            if (type.name().equalsIgnoreCase(dataType)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}