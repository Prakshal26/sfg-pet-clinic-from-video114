package guru.springframework.sfgpetclinic.model;

import java.io.Serializable;

public class BaseEntity implements Serializable {

    /*
    We have use box type i.e Long instead of long. As this is the requirement of hibernate
    We use box type as they are null by default
     */
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
