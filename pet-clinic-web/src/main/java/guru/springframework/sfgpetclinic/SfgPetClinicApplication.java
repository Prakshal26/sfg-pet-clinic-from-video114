package guru.springframework.sfgpetclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
This SpringBootApplication will go and look for all the controllers in the project.
 */
@SpringBootApplication
public class SfgPetClinicApplication {

    public static void main(String[] args) {
        SpringApplication.run(SfgPetClinicApplication.class, args);
    }

}

