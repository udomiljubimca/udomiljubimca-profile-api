package com.java.profileservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@ApiModel(description = "Profile search Dto details")
public class ProfileSearchDto {

    @ApiModelProperty(value = "${ProfileSearchDto.cityId}")
    private long cityId;

    @ApiModelProperty(value = "${ProfileSearchDto.typeId}")
    private long typeId;
}
