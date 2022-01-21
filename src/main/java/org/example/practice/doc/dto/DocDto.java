package org.example.practice.doc.dto;

import org.example.practice.user.entity.User;

public class DocDto {

    private Long userId;

    private String number;

    private String date;

    private Long typeDocId;


    public DocDto() {
    }

    public DocDto(String number, String date) {
        this.number = number;
        this.date = date;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTypeDocId() {
        return typeDocId;
    }

    public void setTypeDocId(Long typeDocId) {
        this.typeDocId = typeDocId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
