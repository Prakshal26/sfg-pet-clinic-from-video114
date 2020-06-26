package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;

import java.util.Set;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;

    final long ownerId = 1L;

    final String lastName = "smith";

    @BeforeEach
    void setUp() {

        ownerMapService = new OwnerMapService(new PetTypeMapService(),new PetMapService());

        /*
        Since we are using Project Lombok, in constructor of Owner Class we have given @builder.
        So we are initializing owner map service and putting one object into it.
        Owner.build will make and object for us with id as 1 and then we will save using
        save method of ownermapservice.
         */
        ownerMapService.save(Owner.builder().id(ownerId).lastName(lastName).build());

    }

    @org.junit.jupiter.api.Test
    void findAll() {
        //Find all will return a set so we are creating set to store it.
        Set<Owner> ownerSet = ownerMapService.findAll();

        assertEquals(1,ownerSet.size());
    }

    @org.junit.jupiter.api.Test
    void findById() {

        Owner owner = ownerMapService.findById(ownerId);

        assertEquals(ownerId,owner.getId());
    }

    @org.junit.jupiter.api.Test
    void saveExistingId() {

        long id = 2L;
        Owner owner2 = Owner.builder().id(id).build();

        Owner saveOwner = ownerMapService.save(owner2);

        assertEquals(id, saveOwner.getId());

    }

    @Test
    void saveNoID() {

        Owner savedOwner = ownerMapService.save(Owner.builder().build());
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());

    }

    @org.junit.jupiter.api.Test
    void delete() {

        ownerMapService.delete(ownerMapService.findById(ownerId));
        assertEquals(0,ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {

        ownerMapService.deleteById(ownerId);
        assertEquals(0,ownerMapService.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner smith =  ownerMapService.findByLastName(lastName);

        assertNotNull(smith);
        assertEquals(ownerId, smith.getId());

    }
}