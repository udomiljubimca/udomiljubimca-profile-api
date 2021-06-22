package com.java.profileservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@ApiModel(description = "Profile Dto details")
public class ProfileDto {

    /**
     * association name
     */
    @ApiModelProperty(value = "${ProfileDto.association}")
    private String associationName;

    /**
     * profile name
     */
    @ApiModelProperty(value = "${ProfileDto.profileName}")
    private String profileName;

    /**
     * description
     */
    @ApiModelProperty(value = "${ProfileDto.desciption}")
    private String description;

    /**
     * video link
     */
    @ApiModelProperty(value = "${ProfileDto.videoLink}")
    private String videoLink;

    /**
     * special habits text
     */
    @ApiModelProperty(value = "${ProfileDto.specialHabitsText}")
    private String specialHabitsText;

    /**
     * special needs
     */
    @ApiModelProperty(value = "${ProfileDto.specialNeeds}")
    private String specialNeeds;

    /**
     * special habits
     */
    @ApiModelProperty(value = "${ProfileDto.specialHabits}")
    private boolean specialHabits;

    /**
     * is or not vaccinated
     */
    @ApiModelProperty(value = "${ProfileDto.vaccinated}")
    private boolean vaccinated;

    /**
     * is or not good with kids
     */
    @ApiModelProperty(value = "${ProfileDto.goodWithKids}")
    private boolean goodWithKids;

    /**
     * is or not good with dogs
     */
    @ApiModelProperty(value = "${ProfileDto.goodWithDogs}")
    private boolean goodWithDogs;

    /**
     * is or not good with cats
     */
    @ApiModelProperty(value = "${ProfileDto.goodWithCats}")
    private boolean goodWithCats;

    /**
     * age id
     */
    @ApiModelProperty(value = "${ProfileDto.ageId}")
    private long ageId;

    /**
     * city id
     */
    @ApiModelProperty(value = "${ProfileDto.cityId}")
    private long cityId;

    /**
     * gender id
     */
    @ApiModelProperty(value = "${ProfileDto.genderId}")
    private long genderId;

    /**
     * nature id
     */
    @ApiModelProperty(value = "${ProfileDto.natureId}")
    private long natureId;

    /**
     * size id
     */
    @ApiModelProperty(value = "${ProfileDto.sizeId}")
    private long sizeId;

    /**
     * type id
     */
    @ApiModelProperty(value = "${ProfileDto.typeId}")
    private long typeId;

    /**
     * list of health ids
     */
    @ApiModelProperty(value = "${ProfileDto.healths}")
    private List<Long> healthIds;

}