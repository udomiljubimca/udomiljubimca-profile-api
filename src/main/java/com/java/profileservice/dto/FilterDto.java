package com.java.profileservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@ApiModel(description = "Filter Dto Details")
public class FilterDto {

    /**
     * city id
     */
    @ApiModelProperty(value = "${FilterDto.cityId}")
    private Long cityId;

    /**
     * type id
     */
    @ApiModelProperty(value = "${FilterDto.typeId}")
    private Long typeId;

    /**
     * list of gender ids
     */
    @ApiModelProperty(value = "${FilterDto.genderIds}")
    private List<Long> genderIds = Collections.emptyList();

    /**
     * list of age ids
     */
    @ApiModelProperty(value = "${FilterDto.ageIds}")
    private List<Long> ageIds = Collections.emptyList();

    /**
     * list of size ids
     */
    @ApiModelProperty(value = "${FilterDto.sizeIds}")
    private List<Long> sizeIds = Collections.emptyList();

}
