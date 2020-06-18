    /*
This Class is extending AbstractMapService which has generic defination of all the methons.
Here it will pass it's own object to the methods of base class using super keyword and
operation will be performed based in it.
 */

package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) {
        //return super.save(object.getId(),object);
        //As we have modified the method in AbstractMapService as we are now not stroing ID
        //manually Instead hasmap is genrating for us.
        //So need to modify this as well.
        return super.save(object);
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteByid(id);

    }

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }
}
