package org.example.practice.office.service;

import org.example.practice.office.dto.*;
import org.example.practice.user.dto.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * Сервис
 */
@Validated
public interface OfficeService {

    /**
     * Добавить новый офис в БД
     *
     * @param office
     */
    void add(@Valid OfficeDtoForSave office);

    /**
     * Добавить обновить данные офиса в БД
     *
     * @param office
     */
    void update(@Valid OfficeDtoForUpd office);

    /**
     * Получить офис по ID
     *
     * @param id
     * @return {@Office}
     */
    OfficeDto office(Long id);

    /**
     * Получить список офисов
     *
     * @return {@Office}
     */
    List<OfficeDto> offices();

    /**
     * Получить список офисов, удовлетворяющих условиям запроса
     *
     * @return {@User}
     */
    List<OfficeDtoForListOut> officesByTerms(OfficeDtoForListIn dto);

}
