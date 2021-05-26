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

    @Override
    public void save(Size size) {
        sizeRepository.save(size);
    }

    @Override
    public void deleteAll() {
        sizeRepository.deleteAll();
    }

    @Override
    public void saveAll(List<Size> sizeList) {
        sizeRepository.saveAll(sizeList);
    }

    @Override
    public List<Size> getAllSizes() {
        List<Size> sizeList = sizeRepository.findAll();
        return sizeList;
    }

}
