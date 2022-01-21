package org.example.practice.doc.dao;

import org.example.practice.doc.entity.Doc;
import org.example.practice.user.entity.User;

import java.util.List;

/**
 * DAO для работы с Documents
 */
public interface DocDao {

    /**
     * Получить все объекты Doc
     *
     * @return
     */
    List<Doc> all();

    /**
     * Получить Doc по идентификатору
     *
     * @param id
     * @return
     */
    Doc loadById(Long id);

    /**
     * Получить Doc по номеру
     *
     * @param number
     * @return
     */
    Doc loadByNumber(String number);

    /**
     * Сохранить документ
     *
     * @param doc
     */
    void save(Doc doc);

    /**
     * Обновить данные документа
     *
     * @param doc
     */
    void update(Doc doc);

}
