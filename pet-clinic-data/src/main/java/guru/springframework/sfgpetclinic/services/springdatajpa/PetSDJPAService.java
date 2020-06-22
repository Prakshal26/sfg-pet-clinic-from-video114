/*
Completed till video 154. We already had MAP Services but we were not using Tables and entity. Like in PRevious videos we have created
entities so we need service based on those. So to solve this we have created new service for everything. We have not deleted map services we can still u
se them. In new Services we have given Profile so we can switch between the profiles to use any service"

 */

package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class PetSDJPAService implements PetService {

    private final PetRepository petrepository;

    public PetSDJPAService(PetRepository petrepository) {
        this.petrepository = petrepository;
    }

    @Override
    public Set<Pet> findAll() {
        Set<Pet> pets = new HashSet<>();
        petrepository.findAll().forEach(pets::add);
        return pets;
    }

    @Override
    public Pet findById(Long aLong) {
        return petrepository.findById(aLong).orElse(null);
    }

    @Override
    public Pet save(Pet object) {
        return petrepository.save(object);
    }

    @Override
    public void delete(Pet object) {
        petrepository.delete(object);

    }

    @Override
    public void deleteById(Long aLong) {
        petrepository.deleteById(aLong);

    }
}
