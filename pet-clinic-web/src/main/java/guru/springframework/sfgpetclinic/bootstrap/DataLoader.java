package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.map.OwnerServiceMap;
import guru.springframework.sfgpetclinic.services.map.VetServiceMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/*
By making this as a component it becomes the spring bean and get registered. SO when it is registered
it is going to execute the run method.
i.e even if we do not call this class in run main application class it will still be executed as
it has it's own run method.
NOTE: We are not saying that this run is because of Component we have given, but this run method
is the method of CommandLineRunner interface.
 */
//ComandLineRunner is the inbuild interface provided by SPringBoot
@Component
public class DataLoader implements CommandLineRunner {

    //Owner Service is the class we have made in pet-clinic-data repo.
    private final OwnerService ownerService;
    private final VetService vetService;
    /* In Video 81 we will comment this part and will make SPring to itself create the object
    and do the intialization
     */
    //public DataLoader() {

        //ownerService = new OwnerServiceMap();
        //vetService = new VetServiceMap();
    //}
/*
This is how Spring will do the initialization for us by creating the constructor and giving
the autowired.
In OwnerService class, VetService Class and PEt we have given
@service to specify that yes that is the service. HEre we are giving autowired that this
is a service and we need not to create object manyally by giving new.
Autowired: To tell SpringBoot that Owner Service is a kind of service so just create the object of
that service internally.
 */

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    //Run method is in CommandLineRunner Interface provided to us by SpringBoot
    @Override
    public void run(String... args) throws Exception {
/*
Here we are adding data to our project. Generally Spring provides better functionality to add the data
But for the sake of understanding we are adding data traditionally.
 */
        /*
        We have created the project of Owner class. Owner has extende PErson class which have
        the function setfirstname and last name. And in turn Person class has extended BaseEntity
        class which has the implementation of setid function.
         */

        //Modified in video 84 now no need to give id manually. It will fetch already existing last id, increase it by 1
        //and store it at that id. We have implemented this is AbstractMapService class.
        Owner owner1 = new Owner();
        //owner1.setId(1L);
        owner1.setFirstName("Michael");
        owner1.setLastName("weston");

        /*
        Save is the function in OwenerService map. If we will see above we have given inside constructor
        that owner=new OwnerServiceMap. i.e object of owner service map and hence it has the function save
        inside it.
         */
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        //owner2.setId(2L);
        owner2.setFirstName("Fiona");
        owner2  .setLastName("Glence");

        ownerService.save(owner2);

        Vet vet1 = new Vet();
        //Modified in video 84 now no need to give id manually. It will fetch already existing last id, increase it by 1
        //and store it at that id. We have implemented this is AbstractMapService class.
        //vet1.setId(1L);
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        //vet2.setId(2L);
        vet2.setFirstName("Sammy");
        vet2.setLastName("Axeee");

        vetService.save(vet2);

        System.out.println("Loading Vet");
    }
}
