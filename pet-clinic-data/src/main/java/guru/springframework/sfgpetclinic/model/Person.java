package guru.springframework.sfgpetclinic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Person extends BaseEntity {

    public Person(Long id, String firstName, String lastName) {
        //Person Extends BAseEntity, But no constructoret there because we have given it as
        //no args constructor from lombok so it will create constructor automatically and wil work
        super(id);
        this.firstName=firstName;
        this.lastName=lastName;
    }

    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")//In DB we are creating a column with this name.
    //If we will not give volumn name than also Hibernate will create table with column name of variable.
    private String lastName;


}
