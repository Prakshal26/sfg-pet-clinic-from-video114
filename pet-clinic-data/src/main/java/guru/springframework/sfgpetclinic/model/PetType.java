package guru.springframework.sfgpetclinic.model;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


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
@Table(name = "types")
public class PetType extends BaseEntity {

    @Column(name = "name")
    private String name;

}
