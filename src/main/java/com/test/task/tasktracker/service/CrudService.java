package com.test.task.tasktracker.service;

import java.util.List;

public interface CrudService<T> {
    T create(T t);
    T readById(long id);
    T update(long id, T t);
    void delete(long id);
    List<T> getAll();
}
