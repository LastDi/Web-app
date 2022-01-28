package org.example.practice.user.service;

import org.example.practice.user.dto.*;
import org.example.practice.user.entity.User;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * Сервис
 */
@Validated
public interface UserService {

    /**
     * Добавить нового юзера в БД
     *
     * @param user
     */
    void add(@Valid UserDtoForSave user);

    /**
     * Добавить обновить данные юзера в БД
     *
     * @param user
     */
    void update(@Valid UserDtoForUpd user);

    /**
     * Получить список людей
     *
     * @return {@User}
     */
    List<UserDtoForListOut> users();

    /**
     * Получить список людей, удовлетворяющих условиям запроса
     *
     * @return {@User}
     */
    List<UserDtoForListOut> usersByTerms(UserDtoForListIn dto);

    /**
     * Получить человека по ID
     *
     * @param id
     * @return {@User}
     */
    UserDto user(Long id);
}
