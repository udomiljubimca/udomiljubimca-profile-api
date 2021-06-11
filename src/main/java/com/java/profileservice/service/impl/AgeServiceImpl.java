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

    /**
     * Save age into database
     */
    public void save(Age age){
        ageRepository.save(age);
    }

    /**
     * Delete all ages from database
     */
    public void deleteAll(){
        ageRepository.deleteAll();
    }

    /**
     * Save ages into database
     *
     * @param ageList
     */
    public void saveAll(List<Age> ageList){
        ageRepository.saveAll(ageList);
    }

    /**
     * Get all ages from database
     *
     * @return List<Age>
     */
    @Override
    public List<Age> getAllAges() {
        List<Age> ageList = ageRepository.findAll();
        return ageList;
    }
}
