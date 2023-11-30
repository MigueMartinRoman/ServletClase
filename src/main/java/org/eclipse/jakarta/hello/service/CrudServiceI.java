package org.eclipse.jakarta.hello.service;

import org.eclipse.jakarta.hello.model.Foto;

import java.util.List;

public interface CrudServiceI<T> {
    default List<T> findAll() throws Exception {
        throw new Exception("Método no implementado");
    }
    default void insert(T t) throws Exception {
        throw new Exception("Método no implementado");
    }
    default void delete(T t) throws Exception {
        throw new Exception("Método no implementado");
    }
    default void update(T t) throws Exception {
        throw new Exception("Método no implementado");
    }
}
