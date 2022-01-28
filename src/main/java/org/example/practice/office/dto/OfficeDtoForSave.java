package org.example.practice.office.dto;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Validated
public class OfficeDtoForSave {
    @NotNull
    private Long orgId;
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    private String phone;
    private boolean active;

    public OfficeDtoForSave() {
    }

    public OfficeDtoForSave(Long orgId, String name, String address) {
        this.orgId = orgId;
        this.name = name;
        this.address = address;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
