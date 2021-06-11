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

    /**
     * Save gender into database
     */
    public void save(Gender gender) {
        genderRepository.save(gender);
    }

    /**
     * Delete all genders from database
     */
    public void deleteAll() {
        genderRepository.deleteAll();
    }

    /**
     * Save genders into database
     *
     * @param genderList
     */
    public void saveAll(List<Gender> genderList) {
        genderRepository.saveAll(genderList);
    }

    /**
     * Get all genders from database
     *
     * List<Gender>
     */
    @Override
    public List<Gender> getAllGenders() {
        List<Gender> genderList = genderRepository.findAll();
        return genderList;
    }
}
