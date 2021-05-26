package com.java.profileservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class ProfileSearchDTO {
    private long cityId;
    private long typeId;
}
