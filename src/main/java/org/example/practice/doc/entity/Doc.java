package org.example.practice.doc.entity;

import org.example.practice.typedoc.entity.TypeDoc;
import org.example.practice.user.entity.User;

import javax.persistence.*;

@Entity
@Table(name = "Docs")
public class Doc {

    @Id
    private Long userId;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    /**
     * Номер документа
     */
    @Column(name = "number", length = 15, nullable = false)
    private String number;

    /**
     * Дата выдачи документа
     */
    @Column(name = "date", length = 15, nullable = false)
    private String date;

    /**
     * Тип документа
     */
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "type_id", nullable = false)
    private TypeDoc typeDoc;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    public Doc() {
    }

    public Doc(Long userId, User user) {
        this.userId = userId;
        this.user = user;
    }

    public Doc(String number, String date, TypeDoc typeDoc) {
        this.number = number;
        this.date = date;
        this.typeDoc = typeDoc;
    }

    public Doc(Long userId, String number, String date, TypeDoc typeDoc) {
        this.userId = userId;
        this.number = number;
        this.date = date;
        this.typeDoc = typeDoc;
    }

    public Doc(Long userId, String number, String date, TypeDoc typeDoc, User user) {
        this.userId = userId;
        this.number = number;
        this.date = date;
        this.typeDoc = typeDoc;
        this.user = user;
    }

    public Long getId() {
        return userId;
    }

    public String getNumber() {
        return number;
    }

    public String getDate() {
        return date;
    }

    public TypeDoc getTypeDoc() {
        return typeDoc;
    }

    public User getUser() {
        return user;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTypeDoc(TypeDoc typeDoc) {
        this.typeDoc = typeDoc;
    }

    @Override
    public String toString() {
        return "Doc{" +
                "userId=" + userId +
                ", number='" + number + '\'' +
                ", date='" + date + '\'' +
                ", typeDoc=" + typeDoc.getName() +
                '}';
    }
}
