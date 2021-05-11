package com.java.profileservice.service;

import com.java.profileservice.model.Type;
import com.java.profileservice.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {

    @Autowired
    private TypeRepository typeRepository;

    public void save(Type type) {
        typeRepository.save(type);
    }

    public void deleteAll() {
        typeRepository.deleteAll();
    }

    public void saveAll(List<Type> typeList) {
        typeRepository.saveAll(typeList);
    }
}
