package org.example.practice.doc.service;

import org.example.practice.doc.dto.DocDto;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * Сервис
 */
@Validated
public interface DocService {

    /**
     * Добавить новый документ в БД
     *
     * @param docDto
     */
    void add(@Valid DocDto docDto);

    /**
     * Получить список документов
     *
     * @return {@Doc}
     */
    List<DocDto> docs();
}
