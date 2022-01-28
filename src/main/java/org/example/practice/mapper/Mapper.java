package org.example.practice.mapper;

import java.util.Collection;
import java.util.List;

public interface Mapper {

    /**
     * Конвертировать коллекцию Entity в DTO
     *
     * @param entityList, outClass
     * @return {@List<D>}
     */
    <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outCLass);

    /**
     * Конвертировать Entity в DTO
     *
     * @param entity, outClass
     * @return {@D}
     */
    <D, T> D map(final T entity, Class<D> outClass);
}
