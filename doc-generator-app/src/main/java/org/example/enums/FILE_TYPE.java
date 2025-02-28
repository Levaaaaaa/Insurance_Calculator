package org.example.enums;

public enum FILE_TYPE {
    TXT("txt"),
    PDF("pdf");

    public final String value;

    FILE_TYPE(String v) {
        this.value = v;
    }
}
