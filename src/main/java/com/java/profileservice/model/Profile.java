package com.java.profileservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String profileName;

    private String associationName;

    private boolean vaccinated;

    private String videoLink;

    private String description;

    private boolean specialHabits;

    private String specialHabitsText;

    private String specialNeeds;

    private boolean goodWithKids;

    private boolean goodWithDogs;

    private boolean goodWithCats;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name ="date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private ZonedDateTime uploadDate;


    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "ageId")
    private Age age;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "cityId")
    private City city;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "genderId")
    private Gender gender;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "Profile_Health", joinColumns = @JoinColumn(name = "idProfile"), inverseJoinColumns = @JoinColumn(name = "idHealth"))
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Health> healths;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "natureId")
    private Nature nature;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "sizeId")
    private Size size;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "typeId")
    private Type type;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<Image> images;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<UserInfo> favorites;



}
