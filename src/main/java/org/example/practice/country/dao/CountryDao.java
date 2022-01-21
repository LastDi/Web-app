package org.example.practice.country.dao;

import org.example.practice.country.entity.Country;
import org.example.practice.user.entity.User;

import java.util.List;

/**
 * DAO для работы с Country
 */
public interface CountryDao {

    /**
     * Получить все объекты Country
     *
     * @return
     */
    List<Country> all();

    /**
     * Получить Country по идентификатору
     *
     * @param id
     * @return
     */
    Country loadById(Long id);

    /**
     * Получить Contry по названию
     *
     * @param code
     * @return
     */
    Country loadByCode(String code);

    /**
     * Сохранить Country
     *
     * @param country
     */
    void save(Country country);
}
