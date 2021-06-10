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

    @ApiModelProperty(value = "${FilterDto.cityId}")
    private Long cityId;

    @ApiModelProperty(value = "${FilterDto.typeId}")
    private Long typeId;

    @ApiModelProperty(value = "${FilterDto.genderIds}")
    private List<Long> genderIds = Collections.emptyList();

    @ApiModelProperty(value = "${FilterDto.ageIds}")
    private List<Long> ageIds = Collections.emptyList();

    @ApiModelProperty(value = "${FilterDto.sizeIds}")
    private List<Long> sizeIds = Collections.emptyList();

}
