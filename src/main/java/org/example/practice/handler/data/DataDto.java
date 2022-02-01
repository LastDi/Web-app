package org.example.practice.handler.data;

/**
 * DTO для представления
 */
public class DataDto {
    Object data;

    public DataDto() {
    }

    public DataDto(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DataDto{" +
                "data=" + data +
                '}';
    }
}
