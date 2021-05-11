package com.java.profileservice.service;

import com.java.profileservice.config.ApiResponse;
import com.java.profileservice.model.Age;
import com.java.profileservice.repository.AgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgeService {

    @Autowired
    private AgeRepository ageRepository;

    public void save(Age age){
        ageRepository.save(age);
    }

    public void deleteAll(){
        ageRepository.deleteAll();
    }

    public void saveAll(List<Age> ageList){
        ageRepository.saveAll(ageList);
    }
}
