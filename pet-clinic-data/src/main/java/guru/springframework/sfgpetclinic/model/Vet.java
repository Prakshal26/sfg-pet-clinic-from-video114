package guru.springframework.sfgpetclinic.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/*
Now we will use Project Lombok, with this we do not want to explicitly give getters and setters. Automatically
getter and setter are there. Lombok takes care of it. We can specify that by giving @Setter @Getter etc etc
Thing we want to be hidden and taken cared by lombok.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name="vets")
public class Vet extends Person {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="vet_specialties",joinColumns = @JoinColumn(name="vet_id"),
    inverseJoinColumns = @JoinColumn(name="speciality_id"))
    private Set<Speciality> specialities = new HashSet<>();


}
