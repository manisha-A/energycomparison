package com.Utilities;

import org.yecht.Data;

public enum TarrifType {
    VAR("Variable tarrif");

    private String value;

    TarrifType(String value) {
        this.value = value;
    }

    public String getTarrif(){
        return value;
    }
}
