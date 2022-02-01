package org.example.practice.office.dto;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Validated
public class OfficeDtoForUpd {
    @NotNull
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    private String phone;
    private String active;

    public OfficeDtoForUpd() {
    }

    public OfficeDtoForUpd(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String isActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
