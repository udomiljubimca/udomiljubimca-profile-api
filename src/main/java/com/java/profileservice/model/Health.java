package com.java.profileservice.model;


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
public class Health {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "Profile_Health", joinColumns = @JoinColumn(name = "idHealth"), inverseJoinColumns = @JoinColumn(name = "idProfile"))
    private List<Profile> profiles;

    public Health(String name) {
        this.name = name;
    }
}
