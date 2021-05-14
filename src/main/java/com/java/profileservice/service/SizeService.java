package com.java.profileservice.service;

import com.java.profileservice.model.Size;

import java.util.List;

public interface SizeService {

    void save(Size size);

    void deleteAll();

    void saveAll(List<Size> sizeList);

}
