package com.java.profileservice.service;

import com.java.profileservice.model.Coexistence;

import java.util.List;

public interface CoexistenceService {

    void save(Coexistence coexistence);

    void deleteAll();

    void saveAll(List<Coexistence> coexistenceList);

    List<Coexistence> getAllCoexistences();
}
