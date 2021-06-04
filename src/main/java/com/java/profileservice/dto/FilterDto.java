package com.java.profileservice.dto;

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
public class FilterDto {

    private Long cityId;
    private Long typeId;
    private List<Long> genderIds = Collections.emptyList();
    private List<Long> ageIds = Collections.emptyList();
    private List<Long> sizeIds = Collections.emptyList();

}
