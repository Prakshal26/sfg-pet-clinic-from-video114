package guru.springframework.sfgpetclinic.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/*
Now from video 145 we are starting with relations many to many etc etc and creating DB.
MappedSuperClass means all the other class will inherit from this and we do not need table for this class.
We dont need this to be mapped to dB.
 */
@MappedSuperclass
public class BaseEntity implements Serializable {

    /*
    We have use box type i.e Long instead of long. As this is the requirement of hibernate
    We use box type as they are null by default
     */
    /*
    To identity uniquely we need ID. So we are giving id type and Generation type also that is it will
    generated id automatically based on the next avialable id. When we will insert the record MYSQL will provide
    us the id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
