package com.java.profileservice.service;

import com.java.profileservice.model.Nature;

import java.util.List;

public interface NatureService {

    void save(Nature nature);

    void deleteAll();

    void saveAll(List<Nature> natureList);

    List<Nature> getAllNatures();
}
