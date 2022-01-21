package org.example.practice.typedoc.dto;

import java.io.Serializable;

public class TypeDocDto implements Serializable {
    private String name;
    private String code;

    public TypeDocDto() {

    }

    public TypeDocDto(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "name = " + name + ", " +
                "code = " + code + ")";
    }
}
