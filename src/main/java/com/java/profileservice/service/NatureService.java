package com.java.profileservice.service;

import com.java.profileservice.model.Nature;

import java.util.List;

public interface NatureService {

    public void save(Nature nature);

    public void deleteAll();

    public void saveAll(List<Nature> natureList);
}
