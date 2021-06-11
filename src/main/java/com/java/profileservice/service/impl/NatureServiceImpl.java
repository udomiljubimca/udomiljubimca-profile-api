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

    /**
     * Save gender into database
     */
    public void save(Nature nature) {
        natureRepository.save(nature);
    }

    /**
     * Delete all genders from database
     */
    public void deleteAll() {
        natureRepository.deleteAll();
    }

    /**
     * Save all natures into database
     *
     * @param natureList
     */
    public void saveAll(List<Nature> natureList) {
        natureRepository.saveAll(natureList);
    }

    /**
     * Get all natures from database
     *
     * @return List<Nature>
     */
    @Override
    public List<Nature> getAllNatures() {
        List<Nature> natureList = natureRepository.findAll();
        return natureList;
    }
}
