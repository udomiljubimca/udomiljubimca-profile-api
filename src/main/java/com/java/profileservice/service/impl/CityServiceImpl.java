package com.java.profileservice.service.impl;

import com.java.profileservice.model.City;
import com.java.profileservice.repository.CityRepository;
import com.java.profileservice.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    /**
     * Save city into database
     */
    @Override
    public void save(City city) {
        cityRepository.save(city);
    }

    /**
     * Delete all cities from database
     */
    @Override
    public void deleteAll() {
        cityRepository.deleteAll();
    }

    /**
     * Save cities into database
     *
     * @param cityList
     */
    @Override
    public void saveAll(List<City> cityList) {
        cityRepository.saveAll(cityList);
    }

    /**
     * Get all cities from database
     *
     * @return List<City>
     */
    @Override
    public List<City> getAllCities() {
        List<City> cityList = cityRepository.findAll();
        return cityList;
    }
}
