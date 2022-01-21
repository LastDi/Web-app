package org.example.practice.user.dao;

import org.example.practice.user.dto.UserDtoForListIn;
import org.example.practice.user.entity.User;

import java.util.List;

/**
 * DAO для работы с User
 */
public interface UserDao {
    /**
     * Получить все объекты User
     *
     * @return
     */
    List<User> all();

    /**
     * Получить все объекты User, удовлетворяющие условиям
     *
     * @return
     */
    List<User> allByTerms(UserDtoForListIn dto);

    /**
     * Получить User по идентификатору
     *
     * @param id
     * @return
     */
    User loadById(Long id);

    /**
     * Сохранить User
     *
     * @param user
     */
    void save(User user);

    /**
     * Обновить данные User
     *
     * @param user
     */
    void update(User user);

}
