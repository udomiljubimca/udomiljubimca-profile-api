//package com.java.profileservice;
//
//
//import com.java.profileservice.model.*;
//import com.java.profileservice.service.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Service;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Service
//public class DbInit implements CommandLineRunner {
//
//    @Autowired
//    private AgeService ageService;
//
//    @Autowired
//    private GenderService genderService;
//
//    @Autowired
//    private NatureService natureService;
//
//    @Autowired
//    private TypeService typeService;
//
//    @Autowired
//    private SizeService sizeService;
//
//    @Autowired
//    private HealthService healthService;
//
//    @Autowired
//    private CityService cityService;
//
//    @Autowired
//    private CoexistenceService coexistenceService;
//
//    @Autowired
//    private ProfileService profileService;
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        profileService.deleteAll();
//
//        //Age
//        this.ageService.deleteAll();
//        Age age1 = new Age("Junior");
//        Age age2 = new Age("Adult");
//        Age age3 = new Age("Senior");
//        List<Age> ageList = Arrays.asList(age1, age2, age3);
//
//        this.ageService.saveAll(ageList);
//
//        this.genderService.deleteAll();
//        Gender gender1 = new Gender("Muški");
//        Gender gender2 = new Gender("Ženski");
//        List<Gender> genderList = Arrays.asList(gender1, gender2);
//
//        this.genderService.saveAll(genderList);
//
//        this.natureService.deleteAll();
//
//        Nature nature1 = new Nature("Hiperaktivan");
//        Nature nature2 = new Nature("Agresivan");
//        Nature nature3 = new Nature("Društven");
//        Nature nature4 = new Nature("Plašljiv");
//        Nature nature5 = new Nature("Smiren");
//        List<Nature> natureList = Arrays.asList(nature1, nature2, nature3, nature4, nature5);
//
//        this.natureService.saveAll(natureList);
//
//        this.typeService.deleteAll();
//        Type type1 = new Type("Pas");
//        Type type2 = new Type("Mačka");
//        Type type3 = new Type("Ostalo");
//        List<Type> typeList = Arrays.asList(type1, type2, type3);
//        this.typeService.saveAll(typeList);
//
//        this.sizeService.deleteAll();
//        Size size1 = new Size("Mali");
//        Size size2 = new Size("Srednji");
//        Size size3 = new Size("Veliki");
//        List<Size> sizeList = Arrays.asList(size1, size2, size3);
//        this.sizeService.saveAll(sizeList);
//
//        this.healthService.deleteAll();
//        Health health1 = new Health("Sterilisan");
//        Health health2 = new Health("Posebne potrebe");
//        List<Health> healthList = Arrays.asList(health1, health2);
//        this.healthService.saveAll(healthList);
//
//        this.cityService.deleteAll();
//        City city1 = new City("Beograd");
//        City city2 = new City("Novi Sad");
//        City city3 = new City("Nis");
//        List<City> cityList = Arrays.asList(city1, city2, city3);
//        this.cityService.saveAll(cityList);
//
//        this.coexistenceService.deleteAll();
//        Coexistence coexistence1 = new Coexistence("Decom");
//        Coexistence coexistence2 = new Coexistence("Psima");
//        Coexistence coexistence3 = new Coexistence("Mackama");
//        List<Coexistence> coexistenceList = Arrays.asList(coexistence1, coexistence2, coexistence3);
//        this.coexistenceService.saveAll(coexistenceList);
//
//    }
//}
