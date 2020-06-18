/*
We have created an interface which will have all the common functions such as findall
findby id, save, delete etc etc.
In general CurdRepository is present in spring framework but for learning point of view we have
created it from scratch.
Curd will take two thing: The type i.e Owner,PEt,Vet etc and it's Id and will perform
operations accoirdingly.
 */

package guru.springframework.sfgpetclinic.services;

import java.util.Set;

public interface CurdService<T,ID> {

    Set<T> findAll();

    T findById(ID id);

    T save(T object);

    void delete(T object);
    void deleteById(ID id);
}
