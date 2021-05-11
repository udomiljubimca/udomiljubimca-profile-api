package com.java.profileservice;


import com.java.profileservice.model.Age;
import com.java.profileservice.model.Gender;
import com.java.profileservice.service.AgeService;
import com.java.profileservice.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DbInit implements CommandLineRunner {

    @Autowired
    private AgeService ageService;

    @Autowired
    private GenderService genderService;

    @Override
    public void run(String... args) throws Exception {
        
        //Age
        this.ageService.deleteAll();
        Age age1 = new Age("Junior");
        Age age2 = new Age("Adult");
        Age age3 = new Age("Senior");
        List<Age> ageList = Arrays.asList(age1, age2, age3);

        this.ageService.saveAll(ageList);

        // TODO: 11.5.21. City, Coexistance, Gender, Health, Nature, Size, Type

        this.genderService.deleteAll();
        Gender gender1 = new Gender("male");
        Gender gender2 = new Gender("female");
        List<Gender> genderList = Arrays.asList(gender1, gender2);

        this.genderService.saveAll(genderList);

    }
}
