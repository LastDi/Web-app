package org.example.practice.typedoc.dao;


import org.example.practice.country.entity.Country;
import org.example.practice.typedoc.entity.TypeDoc;

import java.util.List;

/**
 * DAO для работы с TypeDoc
 */
public interface TypeDocDao {
    /**
     * Получить все типы документов
     *
     * @return
     */
    List<TypeDoc> all();

    /**
     * Получить тип документа по идентификатору
     *
     * @param id
     * @return
     */
    TypeDoc loadById(Long id);

    /**
     * Получить тип документа по названию
     *
     * @param name
     * @return
     */
    TypeDoc loadByName(String name);

    /**
     * Сохранить TypeDoc
     *
     * @param typeDoc
     */
    void save(TypeDoc typeDoc);
}
