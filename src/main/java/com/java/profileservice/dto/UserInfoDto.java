package com.java.profileservice.dto;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class UserInfoDto {

    private Long userId;
    private Long profileId;
}
