package com.java.profileservice.service.impl;

import com.java.profileservice.model.Gender;
import com.java.profileservice.repository.GenderRepository;
import com.java.profileservice.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenderServiceImpl implements GenderService {

    @Autowired
    private GenderRepository genderRepository;

    public void save(Gender gender) {
        genderRepository.save(gender);
    }

    public void deleteAll() {
        genderRepository.deleteAll();
    }

    public void saveAll(List<Gender> genderList) {
        genderRepository.saveAll(genderList);
    }

    @Override
    public List<Gender> getAllGenders() {
        List<Gender> genderList = genderRepository.findAll();
        return genderList;
    }
}
