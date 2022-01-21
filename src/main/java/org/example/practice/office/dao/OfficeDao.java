package org.example.practice.office.dao;

import org.example.practice.office.dto.OfficeDto;
import org.example.practice.office.dto.OfficeDtoForListIn;
import org.example.practice.office.entity.Office;
import org.example.practice.user.dto.UserDtoForListIn;
import org.example.practice.user.entity.User;

import java.util.List;

/**
 * DAO для работы с Office
 */
public interface OfficeDao {

    /**
     * Получить все объекты Office
     *
     * @return
     */
    List<Office> all();

    /**
     * Получить все объекты Office, удовлетворяющие условиям
     *
     * @return
     */
    List<Office> allByTerms(OfficeDtoForListIn dto);

    /**
     * Получить Office по идентификатору
     *
     * @param id
     * @return
     */
    Office loadById(Long id);

    /**
     * Сохранить Office
     *
     * @param office
     */
    void save(Office office);

    /**
     * Обновить данные Office
     *
     * @param office
     */
    void update(Office office);
}
