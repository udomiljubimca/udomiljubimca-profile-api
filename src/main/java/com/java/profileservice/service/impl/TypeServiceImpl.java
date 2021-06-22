package com.java.profileservice.service.impl;

import com.java.profileservice.model.Type;
import com.java.profileservice.repository.TypeRepository;
import com.java.profileservice.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    /**
     * Save type into database
     */
    public void save(Type type) {
        typeRepository.save(type);
    }

    /**
     * Delete all types from database
     */
    public void deleteAll() {
        typeRepository.deleteAll();
    }

    /**
     * Save all types into database
     *
     * @param typeList
     */
    public void saveAll(List<Type> typeList) {
        typeRepository.saveAll(typeList);
    }

    /**
     * Get all types from database
     *
     * @return List<Type>
     */
    @Override
    public List<Type> getAllTypes() {
        List<Type> typeList = typeRepository.findAll();
        return typeList;
    }
}
