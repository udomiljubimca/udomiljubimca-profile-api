package com.java.profileservice.service;

import com.java.profileservice.model.Image;

import java.util.List;

public interface ImageService {

    void saveAll(List<Image> imageList);

    List<Image> getAll();
}
