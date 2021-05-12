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
    private boolean specialHabits;
    private boolean vaccinated;
    private long ageId;
    private long cityId;
    private long coexistenceId;
    private long genderId;
    private long healthId;
    private long natureId;
    private long sizeId;
    private long typeId;
    private List<Long> imagesIds;


}