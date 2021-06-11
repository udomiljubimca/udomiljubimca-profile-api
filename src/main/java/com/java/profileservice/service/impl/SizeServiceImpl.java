package com.java.profileservice.service.impl;


import com.java.profileservice.model.Size;
import com.java.profileservice.repository.SizeRepository;
import com.java.profileservice.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeServiceImpl implements SizeService {

    @Autowired
    private SizeRepository sizeRepository;

    /**
     * Save size into database
     */
    @Override
    public void save(Size size) {
        sizeRepository.save(size);
    }

    /**
     * Delete all sizes from database
     */
    @Override
    public void deleteAll() {
        sizeRepository.deleteAll();
    }

    /**
     * Save all sizes into database
     *
     * @param sizeList
     */
    @Override
    public void saveAll(List<Size> sizeList) {
        sizeRepository.saveAll(sizeList);
    }

    /**
     * Get all sizes from database
     *
     * @return List<Size>
     */
    @Override
    public List<Size> getAllSizes() {
        List<Size> sizeList = sizeRepository.findAll();
        return sizeList;
    }

}
