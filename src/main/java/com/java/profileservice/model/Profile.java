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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "${Profile.id}")
    private long id;

    @ApiModelProperty(value = "${Profile.profileName}")
    private String profileName;

    @ApiModelProperty(value = "${Profile.associationName}")
    private String associationName;

    @ApiModelProperty(value = "${Profile.vaccinated}")
    private boolean vaccinated;

    @ApiModelProperty(value = "${Profile.videoLink}")
    private String videoLink;

    @ApiModelProperty(value = "${Profile.profileDescription}")
    private String description;

    @ApiModelProperty(value = "${Profile.specialHabits}")
    private boolean specialHabits;

    @ApiModelProperty(value = "${Profile.specialHabitsText}")
    private String specialHabitsText;

    @ApiModelProperty(value = "${Profile.specialNeeds}")
    private String specialNeeds;

    @ApiModelProperty(value = "${Profile.goodWithKids}")
    private boolean goodWithKids;

    @ApiModelProperty(value = "${Profile.goodWithDogs}")
    private boolean goodWithDogs;

    @ApiModelProperty(value = "${Profile.goodWithCats}")
    private boolean goodWithCats;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name ="date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @ApiModelProperty(value = "${Profile.date}")
    private ZonedDateTime uploadDate;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "ageId")
    @ApiModelProperty(value = "${Profile.age}")
    private Age age;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "cityId")
    @ApiModelProperty(value = "${Profile.city}")
    private City city;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "genderId")
    @ApiModelProperty(value = "${Profile.gender}")
    private Gender gender;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "Profile_Health", joinColumns = @JoinColumn(name = "idProfile"), inverseJoinColumns = @JoinColumn(name = "idHealth"))
    @Fetch(value = FetchMode.SUBSELECT)
    @ApiModelProperty(value = "${Profile.healths}")
    private List<Health> healths;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "natureId")
    @ApiModelProperty(value = "${Profile.nature}")
    private Nature nature;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "sizeId")
    @ApiModelProperty(value = "${Profile.size}")
    private Size size;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "typeId")
    @ApiModelProperty(value = "${Profile.type}")
    private Type type;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    @ApiModelProperty(value = "${Profile.images}")
    private List<Image> images;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    @ApiModelProperty(value = "${Profile.favourites}")
    private List<UserInfo> favorites;



}
