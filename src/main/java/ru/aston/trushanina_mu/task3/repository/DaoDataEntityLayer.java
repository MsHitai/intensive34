package ru.aston.trushanina_mu.task3.repository;

import java.util.List;

public interface DaoDataEntityLayer<T> {
    List<T> findAll();

    T findEntityById(long id);

    boolean delete(long id);

    boolean create(T t);

    T update(T t);
}
