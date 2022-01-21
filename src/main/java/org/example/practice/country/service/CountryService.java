package org.example.practice.country.service;

import org.example.practice.country.dto.CountryDto;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * Гражданство
 */
@Validated
public interface CountryService {

    /**
     * Добавить новую страну гражданства в БД
     *
     * @param country
     */
    void add(@Valid CountryDto country);

    /**
     * Получить список стран
     *
     * @return {@Country}
     */
    List<CountryDto> countries();
}
