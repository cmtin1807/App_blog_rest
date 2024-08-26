package com.example.app_blog.service;

import java.util.List;
import java.util.Optional;

public interface IGenerateService <T>{

    List<T> findAll();

    Optional<T> findById(Long id);

    void save(T t);

    void remove(Long id);

}
