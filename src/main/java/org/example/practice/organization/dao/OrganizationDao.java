package org.example.practice.organization.dao;

import org.example.practice.organization.dto.OrganizationDto;
import org.example.practice.organization.dto.OrganizationDtoForListIn;
import org.example.practice.organization.entity.Organization;
import org.example.practice.user.entity.User;

import java.util.List;

/**
 * DAO для работы с Organization
 */
public interface OrganizationDao {
    /**
     * Получить все объекты Organization
     *
     * @return
     */
    List<Organization> all();

    /**
     * Получить все объекты Organization, удовлетворяющие условиям запроса
     *
     * @return
     */
    List<Organization> allByTerms(OrganizationDtoForListIn dto);

    /**
     * Получить Organization по идентификатору
     *
     * @param id
     * @return
     */
    Organization loadById(Long id);

    /**
     * Сохранить Organization
     *
     * @param organization
     */
    void save(Organization organization);

    /**
     * Обновить данные Organization
     *
     * @param organization
     */
    void update(Organization organization);
}
