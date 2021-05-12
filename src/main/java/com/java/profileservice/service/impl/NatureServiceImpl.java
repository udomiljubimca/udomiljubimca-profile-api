package com.java.profileservice.service.impl;

import com.java.profileservice.model.Nature;
import com.java.profileservice.repository.NatureRepository;
import com.java.profileservice.service.NatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NatureServiceImpl implements NatureService {

    @Autowired
    private NatureRepository natureRepository;

    public void save(Nature nature) {
        natureRepository.save(nature);
    }

    public void deleteAll() {
        natureRepository.deleteAll();
    }

    public void saveAll(List<Nature> natureList) {
        natureRepository.saveAll(natureList);
    }
}
