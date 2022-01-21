package org.example.practice.user.entity;


import org.example.practice.country.entity.Country;
import org.example.practice.doc.entity.Doc;
import org.example.practice.office.entity.Office;

import javax.persistence.*;

/**
 * Человек
 */
@Entity
@Table(name = "User")
public class User {

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
     * Иия
     */
    @Column(name = "first_name", length = 20, nullable = false)
    private String firstName;

    /**
     * Фамилия
     */
    @Column(name = "last_name", length = 20)
    private String lastName;

    /**
     * Отчетсво
     */
    @Column(name = "middle_name", length = 20)
    private String middleName;

    /**
     * Должность
     */
    @Column(name = "position", length = 30, nullable = false)
    private String position;

    /**
     * Телефон
     */
    @Column(name = "phone", length = 20)
    private String phone;

    /**
     * Офис
     */
    @ManyToOne
    @JoinColumn(name = "office_id", nullable = false)
    private Office office;

    /**
     * Документ
     */
    @OneToOne(
            mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
            )
    private Doc doc;


    /**
     * Гражданство
     */
    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    /**
     * Идентификация БОРНА "ЛШТШФУМ Ащьф"
     */
    private Boolean identified;

    /**
     * Конструктор для hibernate
     */
    public User() {}

    public User(String firstName, String lastName, String middleName, String position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.position = position;
    }

    public User(String firstName, String lastName, String middleName, String position, String phone, Office office, Country country, Boolean identified) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.position = position;
        this.phone = phone;
        this.office = office;
        this.country = country;
        this.identified = identified;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getPosition() {
        return position;
    }

    public String getPhone() {
        return phone;
    }

    public Office getOffice() {
        return office;
    }

    public Doc getDoc() {
        return doc;
    }

    public Country getCountry() {
        return country;
    }

    public Boolean getIdentified() {
        return identified;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public void setDoc(Doc doc) {
        this.doc = doc;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setIdentified(Boolean identified) {
        this.identified = identified;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", thirdName='" + middleName + '\'' +
                ", position='" + position + '\'' +
                ", phone='" + phone + '\'' +
                ", office=" + office +
                ", doc=" + doc +
                ", country=" + country +
                ", identified=" + identified +
                '}';
    }
}
