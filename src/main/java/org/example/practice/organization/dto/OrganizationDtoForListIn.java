package org.example.practice.organization.dto;

public class OrganizationDtoForListIn {
    private String inn;
    private String name;
    private boolean active;

    public OrganizationDtoForListIn() {
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "OrganizationDtoForListIn{" +
                "inn='" + inn + '\'' +
                ", name='" + name + '\'' +
                ", active=" + active +
                '}';
    }
}
