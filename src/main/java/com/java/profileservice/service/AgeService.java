package com.java.profileservice.service;

import com.java.profileservice.model.Age;

import java.util.List;

public interface AgeService {

    void save(Age age);

    void deleteAll();

    void saveAll(List<Age> ageList);
}
