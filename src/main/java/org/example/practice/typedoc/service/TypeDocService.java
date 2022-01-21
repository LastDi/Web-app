package org.example.practice.typedoc.service;

import org.example.practice.typedoc.dto.TypeDocDto;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * Сервис
 */
@Validated
public interface TypeDocService {

    /**
     * Добавить новый тип документа в БД
     *
     * @param typeDoc
     */
    void add(@Valid TypeDocDto typeDoc);

    /**
     * Получить список типов документов
     *
     * @return {@TypeDoc}
     */
    List<TypeDocDto> docTypes();
}
