package com.java.profileservice.service.impl;

import com.java.profileservice.model.Coexistence;
import com.java.profileservice.repository.CoexistenceRepository;
import com.java.profileservice.service.CoexistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoexistenceServiceImpl implements CoexistenceService {

    @Autowired
    private CoexistenceRepository coexistenceRepository;

    @Override
    public void save(Coexistence coexistence) {
        coexistenceRepository.save(coexistence);
    }

    @Override
    public void deleteAll() {
        coexistenceRepository.deleteAll();
    }

    @Override
    public void saveAll(List<Coexistence> coexistenceList) {
        coexistenceRepository.saveAll(coexistenceList);
    }
}
