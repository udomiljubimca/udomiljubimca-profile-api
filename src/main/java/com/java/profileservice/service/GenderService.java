package com.java.profileservice.service;

import com.java.profileservice.model.Gender;
import com.java.profileservice.model.Health;

import java.util.List;

public interface GenderService {

    void save(Gender gender);

    void deleteAll();

    void saveAll(List<Gender> genderList);

    List<Gender> getAllGenders();
}
