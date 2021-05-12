package com.java.profileservice.service.impl;

import com.java.profileservice.dto.ProfileDto;
import com.java.profileservice.model.*;
import com.java.profileservice.repository.*;
import com.java.profileservice.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private AgeRepository ageRepository;

    @Autowired
    private GenderRepository genderRepository;

    @Autowired
    private NatureRepository natureRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private HealthRepository healthRepository;

    @Autowired
    private CoexistenceRepository coexistenceRepository;


    public Profile saveProfile(ProfileDto profileDto) throws Exception {

        Profile profile = new Profile();

        profile.setProfileName(profileDto.getProfileName());
        profile.setAssociationName(profileDto.getAssociationName());
        profile.setVaccinated(profileDto.isVaccinated());
        profile.setSpecialHabits(profileDto.isSpecialHabits());
        profile.setSpecialHabitsText(profileDto.getSpecialHabitsText());
        profile.setDescription(profileDto.getDescription());
        profile.setVideoLink(profileDto.getVideoLink());

        Optional<Age> age = ageRepository.findById(profileDto.getAgeId());
        if (age.isPresent()) {
            profile.setAge(age.get());
        } else {
            throw new Exception("Error message");
        }

        Optional<Gender> gender = genderRepository.findById(profileDto.getGenderId());
        if (gender.isPresent()) {
            profile.setGender(gender.get());
        } else {
            throw new Exception("Error message");
        }

        Optional<Nature> nature = natureRepository.findById(profileDto.getNatureId());
        if (nature.isPresent()) {
            profile.setNature(nature.get());
        } else {
            throw new Exception("Error message");
        }

        Optional<Type> type = typeRepository.findById(profileDto.getTypeId());
        if (type.isPresent()) {
            profile.setType(type.get());
        } else {
            throw new Exception("Error message");
        }

        Optional<City> city = cityRepository.findById(profileDto.getCityId());
        if (city.isPresent()) {
            profile.setCity(city.get());
        } else {
            throw new Exception("Error message");
        }

        Optional<Coexistence> coexistence = coexistenceRepository.findById(profileDto.getCoexistenceId());
        if (coexistence.isPresent()) {
            profile.setCoexistence(coexistence.get());
        } else {
            throw new Exception("Error message");
        }

        Optional<Size> size = sizeRepository.findById(profileDto.getSizeId());
        if (size.isPresent()) {
            profile.setSize(size.get());
        } else {
            throw new Exception("Error message");
        }

        profile.setHealths(null);
        profile.setImages(null);

        profileRepository.save(profile);

        return profile;
    }
}
