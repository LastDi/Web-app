package org.example.practice.handler.data;

import java.util.List;

public class DataListDto {
    List<?> data;

    public DataListDto(List<?> data) {
        this.data = data;
    }

    public DataListDto() {
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
