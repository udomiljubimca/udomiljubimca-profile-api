package com.java.profileservice.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.java.profileservice.dto.ProfileDto;
import com.java.profileservice.model.*;
import com.java.profileservice.repository.*;
import com.java.profileservice.service.ImageService;
import com.java.profileservice.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.util.*;

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
    private ImageService imageService;

    @Value("${cloudinary.cloud_name}")
    private String cloudName;

    @Value("${cloudinary.api_key}")
    private String cloudApiKey;

    @Value("${cloudinary.api_secret}")
    private String cloudApiSecret;

    public Profile saveProfile(ProfileDto profileDto, MultipartFile[] multipartFiles) throws Exception {

        Profile profile = new Profile();

        List<Image> images = new ArrayList<>();
        Arrays.asList(multipartFiles).stream().limit(3).forEach(multipartFile -> {
            Image image = new Image();
            try {
                image.setImageLink(uploadImages(multipartFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
            image.setProfile(profile);
            images.add(image);

        });
        imageService.saveAll(images);
        profile.setImages(images);

        profile.setProfileName(profileDto.getProfileName());
        profile.setAssociationName(profileDto.getAssociationName());
        profile.setVaccinated(profileDto.isVaccinated());
        profile.setSpecialHabits(profileDto.isSpecialHabits());
        profile.setSpecialHabitsText(profileDto.getSpecialHabitsText());
        profile.setDescription(profileDto.getDescription());
        profile.setVideoLink(profileDto.getVideoLink());
        profile.setGoodWithKids(profileDto.isGoodWithKids());
        profile.setGoodWithDogs(profileDto.isGoodWithDogs());
        profile.setGoodWithCats(profileDto.isGoodWithCats());

        if (profileDto.getSpecialNeeds() == null || profileDto.getSpecialNeeds().equalsIgnoreCase("")) {
            profile.setSpecialNeeds("Nisu unesene posebne potrebe.");
        } else {
            profile.setSpecialNeeds(profileDto.getSpecialNeeds());
        }

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

        Optional<Size> size = sizeRepository.findById(profileDto.getSizeId());
        if (size.isPresent()) {
            profile.setSize(size.get());
        } else {
            throw new Exception("Error message");
        }

        List<Health> healthList = healthRepository.getHealthByIds(profileDto.getHealthIds());
        profile.setHealths(healthList);

        profileRepository.save(profile);

        return profile;
    }

    @Override
    public void deleteAll() {
        profileRepository.deleteAll();
    }


    @Override
    public String uploadImages(MultipartFile multipartFile) throws IOException {

        File file = this.convertMultipartFileToFile(multipartFile);

        Cloudinary cloudinary =
                new Cloudinary("cloudinary://" + cloudApiKey + ":" + cloudApiSecret + "@" + cloudName);

        Map<?, ?> result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());

        String url = (String) result.get("secure_url");

        file.delete();

        return url;
    }

    @Override
    public Profile getProfileById(Long id) {
        Optional<Profile> profile = profileRepository.findById(id);
        return profile.get();
    }

    @Override
    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }


    //Convert MultipartFile in File
    public File convertMultipartFileToFile(MultipartFile multipartFile) {

        File convFile = new File(multipartFile.getOriginalFilename());
        try {
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(multipartFile.getBytes());
            fos.close();

        } catch (IOException e) {

        }

        return convFile;
    }
}
