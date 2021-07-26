package com.java.profileservice.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.java.profileservice.contoller.ProfileController;
import com.java.profileservice.dto.ProfileDto;
import com.java.profileservice.exceptions.EntityNotExistsException;
import com.java.profileservice.exceptions.ExistsEntityException;
import com.java.profileservice.model.*;
import com.java.profileservice.repository.*;
import com.java.profileservice.service.ImageService;
import com.java.profileservice.service.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

    private static final Logger LOG = LoggerFactory.getLogger(ProfileServiceImpl.class);

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

    /**
     * Save profile into database
     *
     * @param profileDto - Object form FE
     * @param multipartFiles - Images
     * @return Profile
     */
    @Override
    public Profile saveProfile(ProfileDto profileDto, MultipartFile[] multipartFiles) throws Exception {

        LOG.info("Starting save profile method in service.");

        Optional<Profile> profile = Optional.of(new Profile());

        //Metoda proverava imprt da li postoje entiteti i setuje
        LOG.info("Checking entities if exists and set to our new Profile.");
        saveEntitiesAndCheckIfExists(profileDto, profile);

        //Mapira ProfileDto u Profile, proverava special needs i habits
        LOG.info("Starting mapping from DTO in Entity.");
        mapFromDto(profileDto, profile);

        //setujemo date
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("GMT+2"));
        profile.get().setUploadDate(now);
        LOG.info("Date set {}", now);


        //cuvamo slike
        LOG.info("Starting saving images.");
        List<Image> images = imageService.saveAndReturnImages(multipartFiles, profile);
        profile.get().setImages(images);
        LOG.info("Images are saved.");

        try {
            profileRepository.save(profile.get());
            LOG.info("Profile is saved into database.");
        } catch (ExistsEntityException ex) {
            throw new ExistsEntityException("Profile with id: " + profile.get().getId() + " already exists");
        }

        return profile.get();
    }

    /**
     * Delete all profiles from database
     */
    @Override
    public void deleteAll() throws Exception {

        List<Image> images = imageService.getAll();

        for (Image url : images) {
            Cloudinary cloudinary = new Cloudinary(
                    "cloudinary://" + cloudApiKey + ":" + cloudApiSecret + "@" + cloudName);
            cloudinary.api().deleteResources(new ArrayList<>(
                            Arrays.asList(url.getImageLink().substring(66, 86))),
                    ObjectUtils.emptyMap());
        }

        profileRepository.deleteAll();
    }

    /**
     * Delete profile from database using id
     *
     * @param id - id of the Profile
     */
    @Override
    public void deleteById(Long id) throws Exception {

        Optional<Profile> profile = profileRepository.findById(id);
        if (profile.isPresent()) {
            Cloudinary cloudinary = new Cloudinary(
                    "cloudinary://" + cloudApiKey + ":" + cloudApiSecret + "@" + cloudName);
            List<String> urls = profile.get().getImages()
                    .stream().map(Image::getImageLink)
                    .collect(Collectors.toList());

            for (String url : urls) {
                cloudinary.api().deleteResources(new ArrayList<>(
                                Arrays.asList(url.substring(66, 86))),
                        ObjectUtils.emptyMap());
            }
        }

        try {
            profileRepository.deleteById(id);
        } catch (Exception e) {
            throw new EntityNotExistsException("Profile with id:" + id + " not found");
        }

    }

    /**
     * Get profile from database using id
     *
     * @param id - id of the Profile
     * @return Profile
     */
    @Override
    public Profile getProfileById(Long id) {

        Optional<Profile> profile = profileRepository.findById(id);

        if (!profile.isPresent()) {
            throw new EntityNotExistsException("Profile with id: " + id + " not found");
        }

        return profile.get();
    }

    /**
     * Get all profiles from database
     *
     * @return List<Profile>
     */
    @Override
    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    @Override
    public List<Profile> getAllByTypeId(Long typeId) {

        Optional<Type> type = typeRepository.findById(typeId);

        if (type.isPresent()) {
            return profileRepository.findAllByTypeId(typeId);
        } else {
            return new ArrayList<>();
        }

    }

    /**
     * Get profiles from database using cityId
     *
     * @param cityId - city id
     * @param page - number of page
     * @return List<Profile>
     */
    @Override
    public List<Profile> getAllByCityId(Long cityId, int page) {

        Optional<City> city = cityRepository.findById(cityId);
        Pageable pageable = PageRequest.of(page, 2);

        if (city.isPresent()) {
            return profileRepository.findAllByCityId(cityId, pageable);
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Update profile
     *
     * @param id - profile id
     * @param profileDto - profile form FE
     * @return Profile
     */
    @Override
    public Profile updateProfile(Long id, ProfileDto profileDto) throws Exception {

        Optional<Profile> profile = profileRepository.findById(id);
        if (!profile.isPresent()) {
            throw new EntityNotExistsException("Profile with id : " + id + " does not exist");
        }

        checkIfEntityUpdateAndSave(profileDto, profile);

        mapFromDto(profileDto, profile);

        profileRepository.save(profile.get());

        return profile.get();
    }

    /**
     * Search profiles using cityId and typeId
     *
     * @param cityId - city id
     * @param typeId - type id
     * @return List<Profile>
     */
    @Override
    public List<Profile> profileSearch(long cityId, long typeId, int page) {

        Optional<City> city = cityRepository.findById(cityId);
        Optional<Type> type = typeRepository.findById(typeId);

        Pageable pageable = PageRequest.of(page, 16);

        if (city.isPresent() && type.isPresent()) {
            return profileRepository.searchProfile(cityId, typeId, pageable);
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Filter profiles
     *
     * @param cityId - city id
     * @param typeId - type id
     * @param genderIds - list of gender ids
     * @param ageIds - list of age ids
     * @param sizeIds - list of size ids
     * @return List<Profile>
     */
    @Override
    public List<Profile> filterProfile(Long cityId, Long typeId, List<Long> genderIds, List<Long> ageIds, List<Long> sizeIds) {

        if (genderIds.isEmpty()) {

            genderIds = genderRepository.getAllIds();
        }
        if (ageIds.isEmpty()) {
            ageIds = ageRepository.getAllIds();
        }
        if (sizeIds.isEmpty()) {
            sizeIds = sizeRepository.getAllIds();
        }

        return profileRepository.filterProfile(cityId, typeId, genderIds, ageIds, sizeIds);

    }

    /**
     * Get last eight saved profiles from database ordered by upload date
     *
     * @return List<Profile>
     */
    @Override
    public List<Profile> getLastEightProfiles() {
        return profileRepository.getLastEightProfiles();
    }

    /**
     * Save profile into database
     *
     * @param profile - profile to be saved
     */
    @Override
    public void saveProfile(Profile profile) {
        profileRepository.save(profile);
    }

    /**
     * Map from ProfileDto to Profile
     *
     * @param profileDto - profileDto
     * @param profile - profile
     */
    private void mapFromDto(ProfileDto profileDto, Optional<Profile> profile) {

        profile.get().setProfileName(profileDto.getProfileName());
        profile.get().setAssociationName(profileDto.getAssociationName());
        profile.get().setVaccinated(profileDto.isVaccinated());
        profile.get().setSpecialHabits(profileDto.isSpecialHabits());
        profile.get().setSpecialHabitsText(profileDto.getSpecialHabitsText());
        profile.get().setDescription(profileDto.getDescription());
        profile.get().setVideoLink(profileDto.getVideoLink());
        profile.get().setGoodWithKids(profileDto.isGoodWithKids());
        profile.get().setGoodWithDogs(profileDto.isGoodWithDogs());
        profile.get().setGoodWithCats(profileDto.isGoodWithCats());
        profile.get().setSpecialNeeds(profileDto.getSpecialNeeds());

        if (!profileDto.isSpecialHabits()) {
            profile.get().setSpecialHabitsText("");
        }

        List<Long> healthIds = Collections.emptyList();
        if (!profile.get().getHealths().isEmpty() &&
                profile.get().getHealths() != null) {
            healthIds = profile.get().getHealths().stream()
                    .map(Health::getId)
                    .collect(Collectors.toList());
        }
        if (profile.get().isSpecialHabits()) {
            profile.get().setSpecialHabitsText(profileDto.getSpecialHabitsText());
        } else {
            profile.get().setSpecialHabitsText("Ljubimac nema posebne navike.");
        }

        // TODO: 23/07/2021 Probati da se refaktorise!
        List<Long> allHealthIds = healthRepository.getAllIds();

        if (!healthIds.contains(allHealthIds.get(1))) {
            profile.get().setSpecialNeeds("Ovaj ljubimac nema posebne potrebe.");
        } else {
            profile.get().setSpecialNeeds(profileDto.getSpecialNeeds());
        }
    }

    /**
     * Check if entities exist
     *
     * @param profileDto - profileDto
     * @param profile - profile
     */
    private void saveEntitiesAndCheckIfExists(ProfileDto profileDto, Optional<Profile> profile) throws Exception {
        Optional<Age> age = ageRepository.findById(profileDto.getAgeId());
        if (age.isPresent()) {
            profile.get().setAge(age.get());
        } else {
            throw new EntityNotExistsException("Age entity with id: " + profileDto.getAgeId() + "does not exist");
        }

        Optional<Gender> gender = genderRepository.findById(profileDto.getGenderId());
        if (gender.isPresent()) {
            profile.get().setGender(gender.get());
        } else {
            throw new EntityNotExistsException("Gender entity with id: " + profileDto.getGenderId() + "does not exist");
        }

        Optional<Nature> nature = natureRepository.findById(profileDto.getNatureId());
        if (nature.isPresent()) {
            profile.get().setNature(nature.get());
        } else {
            throw new EntityNotExistsException("Gender Nature with id: " + profileDto.getNatureId() + "does not exist");
        }

        Optional<Type> type = typeRepository.findById(profileDto.getTypeId());
        if (type.isPresent()) {
            profile.get().setType(type.get());
        } else {
            throw new EntityNotExistsException("Type entity with id: " + profileDto.getTypeId() + "does not exist");
        }

        Optional<City> city = cityRepository.findById(profileDto.getCityId());
        if (city.isPresent()) {
            profile.get().setCity(city.get());
        } else {
            throw new EntityNotExistsException("City entity with id: " + profileDto.getCityId() + "does not exist");
        }

        Optional<Size> size = sizeRepository.findById(profileDto.getSizeId());
        if (size.isPresent()) {
            profile.get().setSize(size.get());
        } else {
            throw new EntityNotExistsException("Size entity with id: " + profileDto.getSizeId() + "does not exist");
        }

        if (!profileDto.getHealthIds().isEmpty()) {
            List<Health> healthList =
                    healthRepository.getHealthByIds(profileDto.getHealthIds());
            profile.get().setHealths(healthList);
        }
    }

    /**
     * Check if entities are updated
     *
     * @param profileDto - profileDto
     * @param profile - profile
     */
    private void checkIfEntityUpdateAndSave(ProfileDto profileDto, Optional<Profile> profile) throws Exception {
        if (profile.get().getNature().getId() != profileDto.getNatureId()) {
            Optional<Nature> nature = natureRepository.findById(profileDto.getNatureId());
            if (nature.isPresent()) {
                profile.get().setNature(nature.get());
            } else {
                throw new EntityNotExistsException("Nature with id: " + nature.get().getId() + " not found");
            }
        }
        if (profile.get().getAge().getId() != profileDto.getNatureId()) {
            Optional<Age> age = ageRepository.findById(profileDto.getAgeId());
            if (age.isPresent()) {
                profile.get().setAge(age.get());
            } else {
                throw new EntityNotExistsException("Age with id: " + age.get().getId() + " not found");
            }
        }
        if (profile.get().getGender().getId() != profileDto.getGenderId()) {
            Optional<Gender> gender = genderRepository.findById(profileDto.getGenderId());
            if (gender.isPresent()) {
                profile.get().setGender(gender.get());
            } else {
                throw new EntityNotExistsException("Gender with id: " + gender.get().getId() + " not found");
            }
        }
        if (profile.get().getSize().getId() != profileDto.getSizeId()) {
            Optional<Size> size = sizeRepository.findById(profileDto.getSizeId());
            if (size.isPresent()) {
                profile.get().setSize(size.get());
            } else {
                throw new EntityNotExistsException("Size with id: " + size.get().getId() + " not found");
            }
        }
        if (profile.get().getCity().getId() != profileDto.getCityId()) {
            Optional<City> city = cityRepository.findById(profileDto.getCityId());
            if (city.isPresent()) {
                profile.get().setCity(city.get());
            } else {
                throw new EntityNotExistsException("City with id: " + city.get().getId() + " not found");
            }
        }
        if (profile.get().getType().getId() != profileDto.getTypeId()) {
            Optional<Type> type = typeRepository.findById(profileDto.getTypeId());
            if (type.isPresent()) {
                profile.get().setType(type.get());
            } else {
                throw new EntityNotExistsException("Type with id: " + type.get().getId() + " not found");
            }
        }

        List<Long> healthIds = profile.get().getHealths().stream()
                .map(Health::getId)
                .collect(Collectors.toList());

        List<Health> healths = new ArrayList<>();
        if (profile.get().getHealths().size() == profileDto.getHealthIds().size()) {
            for (Long l : healthIds) {
                for (Long j : profileDto.getHealthIds()) {
                    if (l.longValue() != j.longValue()) {
                        healths.add(healthRepository.getOne(j));
                        profile.get().setHealths(healths);
                    }
                }
            }
        } else {
            healths = healthRepository.getHealthByIds(profileDto.getHealthIds());
            profile.get().setHealths(healths);
        }
    }
}
