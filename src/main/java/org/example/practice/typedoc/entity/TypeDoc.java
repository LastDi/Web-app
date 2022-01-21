package org.example.practice.typedoc.entity;

import javax.persistence.*;

@Entity
@Table(name = "Types_of_docs")
public class TypeDoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    /**
     * Название документа
     */
    @Column(name = "name", length = 15, nullable = false)
    private String name;

    /**
     * Код документа
     */
    @Column(name = "code", length = 15, nullable = false)
    private String code;

    public TypeDoc() {
    }

    public TypeDoc(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "TypeDoc{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
