package com.java.profileservice.service.impl;

import com.java.profileservice.model.Age;
import com.java.profileservice.repository.AgeRepository;
import com.java.profileservice.service.AgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgeServiceImpl  implements AgeService {

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

    @Override
    public List<Age> getAllAges() {
        List<Age> ageList = ageRepository.findAll();
        return ageList;
    }
}
