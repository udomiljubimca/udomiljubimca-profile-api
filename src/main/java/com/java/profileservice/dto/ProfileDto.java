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

    @ApiModelProperty(value = "${ProfileDto.association}")
    private String associationName;

    @ApiModelProperty(value = "${ProfileDto.profileName}")
    private String profileName;

    @ApiModelProperty(value = "${ProfileDto.desciption}")
    private String description;

    @ApiModelProperty(value = "${ProfileDto.videoLink}")
    private String videoLink;

    @ApiModelProperty(value = "${ProfileDto.specialHabitsText}")
    private String specialHabitsText;

    @ApiModelProperty(value = "${ProfileDto.specialNeeds}")
    private String specialNeeds;

    @ApiModelProperty(value = "${ProfileDto.specialHabits}")
    private boolean specialHabits;

    @ApiModelProperty(value = "${ProfileDto.vaccinated}")
    private boolean vaccinated;

    @ApiModelProperty(value = "${ProfileDto.goodWithKids}")
    private boolean goodWithKids;

    @ApiModelProperty(value = "${ProfileDto.goodWithDogs}")
    private boolean goodWithDogs;

    @ApiModelProperty(value = "${ProfileDto.goodWithCats}")
    private boolean goodWithCats;

    @ApiModelProperty(value = "${ProfileDto.ageId}")
    private long ageId;

    @ApiModelProperty(value = "${ProfileDto.cityId}")
    private long cityId;

    @ApiModelProperty(value = "${ProfileDto.genderId}")
    private long genderId;

    @ApiModelProperty(value = "${ProfileDto.natureId}")
    private long natureId;

    @ApiModelProperty(value = "${ProfileDto.sizeId}")
    private long sizeId;

    @ApiModelProperty(value = "${ProfileDto.typeId}")
    private long typeId;

    @ApiModelProperty(value = "${ProfileDto.healths}")
    private List<Long> healthIds;

}