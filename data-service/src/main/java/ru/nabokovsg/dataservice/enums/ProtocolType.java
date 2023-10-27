package ru.nabokovsg.dataservice.enums;

import java.util.Optional;

public enum ProtocolType {

    VISUAL_MEASURING_CONTROL,
    ULTRASONIC_MEASURING_CONTROL,
    GEODESIC_MEASURING,
    ULTRASONIC_CONTROL,
    HARDNESS_MEASURING;

    public static Optional<ProtocolType> from(String protocolType) {
        for (ProtocolType type : values()) {
            if (type.name().equalsIgnoreCase(protocolType)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}