package com.bd.childinfo.forms;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @uathor Bazlur Rahman Rokon
 * @since 5/26/15.
 */
public enum ValidationType {
    NUMBER("number"), EMAIL("email"), PHONE("tel"), TEXT("text"), FILE("file");

    public String name;

    ValidationType(String number) {
        this.name = number;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
