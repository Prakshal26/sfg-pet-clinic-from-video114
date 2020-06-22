package guru.springframework.sfgpetclinic.model;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
//We Want to create a DB of this class. By default JPA will create DB of this class having
// number of columns= number of data member in this class.
//Table name we have given
@Entity
@Table(name="owners")
public class Owner extends Person {

    @Column(name = "address")//Column we are giving Address. By default also jpa will create column name
    private String address;
    @Column(name="city")//If we are not giving column annotation then also JPA will create a column
    private String city;
    @Column(name="telephone")
    private String telephone;

    /*
    Many Pets i.e one owner can have many pets.
    In owner class there is a data member called Pet.

    onetoMany: one here is the class in which we are defining and many is the data member we have defined.
    Here class is Owner and Data memeber in Pets. So one owner many pets.
    Cascade type means if we have deleted owner then pet will also be deleted automatically.

     */
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }
}
