package com.java.profileservice.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor()
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Profile profile;
}
