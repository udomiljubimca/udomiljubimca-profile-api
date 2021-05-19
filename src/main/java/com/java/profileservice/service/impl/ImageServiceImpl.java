package com.java.profileservice.service.impl;

import com.java.profileservice.model.Image;
import com.java.profileservice.repository.ImageRepository;
import com.java.profileservice.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public void saveAll(List<Image> imageList) {
        imageRepository.saveAll(imageList);
    }

    @Override
    public List<Image> getAll() {
        return imageRepository.findAll();
    }
}
