package ru.nabokovsg.dataservice.enums;

import java.util.Optional;

public enum DocumentType {

    REPORT,
    PROTOCOL;

    public static Optional<DocumentType> from(String documentType) {
        for (DocumentType type : values()) {
            if (type.name().equalsIgnoreCase(documentType)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}