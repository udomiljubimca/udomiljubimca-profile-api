package com.java.profileservice.service;

import com.java.profileservice.model.Age;

import java.util.List;

public interface AgeService {

    public void save(Age age);

    public void deleteAll();

    public void saveAll(List<Age> ageList);
}
