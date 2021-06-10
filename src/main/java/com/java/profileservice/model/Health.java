package com.java.profileservice.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@ApiModel(description = "Health details")
public class Health {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "${Health.id}")
    private long id;

    @ApiModelProperty(value = "${Health.name}")
    private String name;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "Profile_Health", joinColumns = @JoinColumn(name = "idHealth"), inverseJoinColumns = @JoinColumn(name = "idProfile"))
    @JsonIgnore
    @ApiModelProperty(value = "${Health.profiles}")
    private List<Profile> profiles;

    public Health(String name) {
        this.name = name;
    }
}
