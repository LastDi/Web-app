package org.example.practice.mapper;

import java.util.Collection;
import java.util.List;

public interface Mapper {
    <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outCLass);
    <D, T> D map(final T entity, Class<D> outClass);
}
