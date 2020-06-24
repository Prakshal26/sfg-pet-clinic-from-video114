package guru.springframework.sfgpetclinic.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

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
@Table(name = "visits")
public class Visit extends BaseEntity {

    @Column(name = "date")
    private LocalDate date;
    @Column(name="description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

}
