package com.java.profileservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@ApiModel(description = "Profile details")
public class Profile {

    /**
     * unique profile id, auto generated
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "${Profile.id}")
    private long id;

    /**
     * profile name
     */
    @ApiModelProperty(value = "${Profile.profileName}")
    private String profileName;

    /**
     * association name
     */
    @ApiModelProperty(value = "${Profile.associationName}")
    private String associationName;

    /**
     * is or not vaccinated
     */
    @ApiModelProperty(value = "${Profile.vaccinated}")
    private boolean vaccinated;

    /**
     * video link
     */
    @ApiModelProperty(value = "${Profile.videoLink}")
    private String videoLink;

    /**
     * description
     */
    @ApiModelProperty(value = "${Profile.profileDescription}")
    private String description;

    /**
     * special habits
     */
    @ApiModelProperty(value = "${Profile.specialHabits}")
    private boolean specialHabits;

    /**
     * special habits text
     */
    @ApiModelProperty(value = "${Profile.specialHabitsText}")
    private String specialHabitsText;

    /**
     * special needs
     */
    @ApiModelProperty(value = "${Profile.specialNeeds}")
    private String specialNeeds;

    /**
     * is or not good with kids
     */
    @ApiModelProperty(value = "${Profile.goodWithKids}")
    private boolean goodWithKids;

    /**
     * is or not good with dogs
     */
    @ApiModelProperty(value = "${Profile.goodWithDogs}")
    private boolean goodWithDogs;

    /**
     * is or not good with cats
     */
    @ApiModelProperty(value = "${Profile.goodWithCats}")
    private boolean goodWithCats;

    /**
     * upload date
     */
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name ="date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @ApiModelProperty(value = "${Profile.date}")
    private ZonedDateTime uploadDate;

    /**
     * age id
     */
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "ageId")
    @ApiModelProperty(value = "${Profile.age}")
    private Age age;

    /**
     * city id
     */
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "cityId")
    @ApiModelProperty(value = "${Profile.city}")
    private City city;

    /**
     * gender id
     */
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "genderId")
    @ApiModelProperty(value = "${Profile.gender}")
    private Gender gender;

    /**
     * list of healths
     */
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "Profile_Health", joinColumns = @JoinColumn(name = "idProfile"), inverseJoinColumns = @JoinColumn(name = "idHealth"))
    @Fetch(value = FetchMode.SUBSELECT)
    @ApiModelProperty(value = "${Profile.healths}")
    private List<Health> healths;

    /**
     * nature id
     */
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "natureId")
    @ApiModelProperty(value = "${Profile.nature}")
    private Nature nature;

    /**
     * size id
     */
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "sizeId")
    @ApiModelProperty(value = "${Profile.size}")
    private Size size;

    /**
     * type id
     */
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "typeId")
    @ApiModelProperty(value = "${Profile.type}")
    private Type type;

    /**
     * list image ids
     */
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    @ApiModelProperty(value = "${Profile.images}")
    private List<Image> images;

    /**
     * list of likes
     */
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    @ApiModelProperty(value = "${Profile.favourites}")
    private List<UserInfo> favorites;



}
