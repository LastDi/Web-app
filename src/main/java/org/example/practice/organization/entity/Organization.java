package org.example.practice.organization.entity;

import org.example.practice.office.entity.Office;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Organization")
public class Organization {

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
     * Название организации
     */
    @Column(name = "name", length = 20, nullable = false)
    private String name;

    /**
     * Полное название организации
     */
    @Column(name = "full_name", length = 20, nullable = false)
    private String fullName;

    /**
     * ИНН организации
     */
    @Column(name = "inn", length = 10, nullable = false)
    private String inn;

    /**
     * КПП организации
     */
    @Column(name = "kpp", length = 9, nullable = false)
    private String kpp;

    /**
     * Адрес организации
     */
    @Column(name = "address", length = 50, nullable = false)
    private String address;

    /**
     * Телефон организации
     */
    @Column(name = "phone", length = 20)
    private String phone;

    /**
     * Состояние организации
     */
    @Column(name = "active")
    private boolean active;

    /**
     * Конструктор для hibernate
     */
    public Organization() {}

    public Organization(String name, String fullName, String inn, String kpp, String address, String phone, boolean active) {
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
        this.phone = phone;
        this.active = active;
    }

    public Organization(Long id, String name, String fullName, String inn, String kpp, String address, String phone, boolean active) {
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", inn='" + inn + '\'' +
                ", kpp='" + kpp + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", active=" + active +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return isActive() == that.isActive()
                && getId().equals(that.getId())
                && getName().equals(that.getName())
                && getFullName().equals(that.getFullName())
                && getInn().equals(that.getInn())
                && getKpp().equals(that.getKpp())
                && getAddress().equals(that.getAddress())
                && Objects.equals(getPhone(), that.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getFullName(), getInn(), getKpp(), getAddress(), getPhone(), isActive());
    }
}
