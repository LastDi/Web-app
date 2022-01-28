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
     * Получить список документов
     *
     * @return {@Doc}
     */
    List<DocDto> docs();
}
