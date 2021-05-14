package com.java.profileservice.service;

import com.java.profileservice.model.Type;

import java.util.List;

public interface TypeService {

    void save(Type type);

    void deleteAll();

    void saveAll(List<Type> typeList);
}
