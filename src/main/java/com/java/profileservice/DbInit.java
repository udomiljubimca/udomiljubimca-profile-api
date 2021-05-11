package com.java.profileservice;


import com.java.profileservice.model.Age;
import com.java.profileservice.model.Type;
import com.java.profileservice.service.AgeService;
import com.java.profileservice.service.TypeService;
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
    private TypeService typeService;

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

        this.typeService.deleteAll();
        Type type1 = new Type("Pas");
        Type type2 = new Type("Mačka");
        Type type3 = new Type("Ostalo");
        List<Type> typeList = Arrays.asList(type1, type2, type3);

        this.typeService.saveAll(typeList);


    }
}
