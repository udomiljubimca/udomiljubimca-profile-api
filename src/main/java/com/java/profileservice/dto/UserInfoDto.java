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
@ApiModel(description = "User info Dto details")
public class UserInfoDto {

    @ApiModelProperty(value = "${UserInfoDto.userId}")
    private Long userId;

    @ApiModelProperty(value = "${UserInfoDto.profileId}")
    private Long profileId;
}
