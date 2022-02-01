package org.example.practice.organization.dto;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Validated
public class OrganizationDtoForUpd {
    @NotNull
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String fullName;
    @NotBlank
    private String inn;
    @NotBlank
    private String kpp;
    @NotBlank
    private String address;
    private String phone;
    private String active;

    public OrganizationDtoForUpd() {
    }

    public OrganizationDtoForUpd(Long id, String name, String fullName, String inn, String kpp, String address) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
    }

    public OrganizationDtoForUpd(Long id, String name, String fullName, String inn, String kpp, String address, String phone, String active) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
        this.phone = phone;
        this.active = active;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
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
