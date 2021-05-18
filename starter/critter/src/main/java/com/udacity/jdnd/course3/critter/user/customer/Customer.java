package com.udacity.jdnd.course3.critter.user.customer;

import com.udacity.jdnd.course3.critter.pet.Pet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Pet> pets;

    @Nationalized
    private String name;

    private String phoneNumber;
    private String notes;
}
