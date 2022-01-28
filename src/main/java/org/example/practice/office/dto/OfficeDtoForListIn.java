package org.example.practice.office.dto;

import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public class OfficeDtoForListIn {
    @NotNull
    private Long orgId;
    private String name;
    private String phone;
    private boolean active;

    public OfficeDtoForListIn() {
    }

    public OfficeDtoForListIn(Long orgId) {
        this.orgId = orgId;
    }

    public OfficeDtoForListIn(Long orgId, boolean active) {
        this.orgId = orgId;
        this.active = active;
    }

    public OfficeDtoForListIn(Long orgId, String name) {
        this.orgId = orgId;
        this.name = name;
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

    @Override
    public String toString() {
        return "OfficeDtoForListIn{" +
                "orgId=" + orgId +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", active=" + active +
                '}';
    }
}
