package com.java.profileservice.service;

import com.java.profileservice.model.Gender;

import java.util.List;

public interface GenderService {

    void save(Gender gender);

    void deleteAll();

    void saveAll(List<Gender> genderList);
}
