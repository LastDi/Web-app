package org.example.practice.office.entity;

import org.example.practice.organization.entity.Organization;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "Office")
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    /**
     * Название офиса
     */
    @Column(name = "name", length = 20, nullable = false)
    private String name;

    /**
     * Адрес офиса
     */
    @Column(name = "address", length = 50, nullable = false)
    private String address;

    /**
     * Номер телефона офиса
     */
    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "active")
    private boolean active;

    /**
     * Организация
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id", nullable = false)
    private Organization organization;

    /**
     * Конструктор для hibernate
     */
    public Office() {
    }

    public Office(String name, String address, String phone, boolean active, Organization organization) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.active = active;
        this.organization = organization;
    }

    public Long getId() {
        return id;
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

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @Override
    public String toString() {
        return "Office{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", active=" + active +
                ", organization=" + organization +
                '}';
    }
}
