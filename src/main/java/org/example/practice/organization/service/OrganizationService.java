package org.example.practice.organization.service;

import org.example.practice.organization.dto.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * Сервис
 */
@Validated
public interface OrganizationService {

    /**
     * Добавить новую организацию в БД
     *
     * @param organization
     */
    void add(@Valid OrganizationDtoForSave organization);

    /**
     * Добавить обновить данные организации в БД
     *
     * @param organization
     */
    void update(@Valid OrganizationDtoForUpd organization);

    /**
     * Получить список организаций, удовлетворяющих условиям запроса
     *
     * @return {@Organization}
     */
    List<OrganizationDtoForListOut> organizationsByTerms(OrganizationDtoForListIn dto);

    /**
     * Получить список организаций
     *
     * @return {@Organization}
     */
    List<OrganizationDto> organizations();

    /**
     * Получить человека по ID
     *
     * @param id
     * @return {@Organization}
     */
    OrganizationDto organization(Long id);
}
