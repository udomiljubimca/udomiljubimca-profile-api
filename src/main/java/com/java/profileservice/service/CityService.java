package com.java.profileservice.service;

import com.java.profileservice.model.City;

import java.util.List;

public interface CityService {

    void save(City city);

    void deleteAll();

    void saveAll(List<City> cityList);
}
