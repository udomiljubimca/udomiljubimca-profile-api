package com.java.profileservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class ProfileSearchDto {

    private long cityId;
    private long typeId;
}
