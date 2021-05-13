package com.java.profileservice.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class ProfileDto {

    private String associationName;
    private String profileName;
    private String description;
    private String videoLink;
    private String specialHabitsText;
    private String specialNeeds;
    private boolean specialHabits;
    private boolean vaccinated;
    private boolean goodWithKids;
    private boolean goodWithDogs;
    private boolean goodWithCats;
    private long ageId;
    private long cityId;
    private long genderId;
    private long natureId;
    private long sizeId;
    private long typeId;
    private List<Long> healthIds;

}