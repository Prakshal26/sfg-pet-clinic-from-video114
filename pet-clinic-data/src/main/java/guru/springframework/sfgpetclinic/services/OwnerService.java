/*
It has extended Curd Service so will have all it's methods such as save,findall etc and it
also has one additional method in it i.e findByLastName which will return the object of class Owner.
 */

package guru.springframework.sfgpetclinic.services;

import guru.springframework.sfgpetclinic.model.Owner;

import java.util.Set;

public interface OwnerService extends  CurdService<Owner, Long> {

    Owner findByLastName(String lastName);

}
