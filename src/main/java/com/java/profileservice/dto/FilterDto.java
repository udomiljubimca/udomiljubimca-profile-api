package com.java.profileservice.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class FilterDto {
    private Long cityId;
    private List<Long> genderIds = new ArrayList<>();
    private List<Long> ageIds = new ArrayList<>();
    private List<Long> sizeIds = new ArrayList<>();

}
