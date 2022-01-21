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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private TypeDoc typeDoc;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    public Doc() {
    }

    public Doc(String number, String date) {
        this.number = number;
        this.date = date;
    }

    public Doc(Long userId, String number, String date, TypeDoc typeDoc, User user) {
        this.userId = userId;
        this.number = number;
        this.date = date;
        this.typeDoc = typeDoc;
        this.user = user;
    }

    public Long getUserId() {
        return userId;
    }

    public Integer getVersion() {
        return version;
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
//                ", user=" + user.getFirstName() +
                '}';
    }
}
