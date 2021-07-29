package com.java.profileservice.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor()
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@ApiModel(description = "User info details")
public class UserInfo {

    /**
     * unique user info, auto generated
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "${UserInfo.id}")
    private Long id;

    /**
     * user id
     */
    @ApiModelProperty(value = "${UserInfo.userId}")
    private Long userId;

    /**
     * liked profile id
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @ApiModelProperty(value = "${UserInfo.profiles}")
    private Profile profile;
}
