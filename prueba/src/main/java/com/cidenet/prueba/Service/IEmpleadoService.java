package com.cidenet.prueba.Service;

import java.util.List;
import java.util.Optional;

public interface IEmpleadoService <T> {

    List<T> findAll();
    Optional<T> findById(Long id);
    T save(T t);
    void delete(Long id);
    String update(T t);



}
