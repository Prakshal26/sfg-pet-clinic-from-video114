/*
This Class will create a Map to store the data for us. This is kind of Genreic map
Like we are just Gving type T and a ID. Based on the object of that Type 'T' it is performing
operations like find all ,delete etc etc.
If you see this : protected Map<ID, T> map =new HashMap<>();
it will create the Map for any Type T we will pass and for that T it will perform operations.
Sp we have created VET,PET, Owner service map class that will have this abstract class
as it's base class and will override the method based on it's own object .

Here Map and Set is inbuilt java classes which allow us to store data based on key,value pair.

 */

package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.BaseEntity;

import java.util.*;

//in Video 84 modified the defination of abstract class and and Map data member.
public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {


    //protected Map<ID, T> map =new HashMap<>();
    protected  Map<Long,T> map = new HashMap<>();

    Set<T> findAll() {
        return new HashSet<>(map.values());
    }
    T findById (ID id) {
        return map.get(id);
    }
    /*
    We have modified this in video 84. Earlier we were manually passing the id and storing, But now we are getching
    the last id already strored incrementing it by 1 in the function getNextId. And then stroing our object at that id
    instead of fetching a new ID.
     */
    //Now only object we are passing we need to store, No need of ID as Hashmap if genrerating it.
    T save(T object) {
        if(object != null) {
            if(object.getId() == null) {
                object.setId(getNextId());
            }
            map.put(object.getId(), object);
        } else {
            throw new RuntimeException("Object cannot be null");
        }
        return object;
    }

    void deleteByid(ID id) {
        map.remove(id);
    }

    void delete(T object) {
        map.entrySet().removeIf(entry->entry.getValue().equals(object));
    }
    private Long getNextId() {

        Long nextId = null;
        try {
          nextId= Collections.max(map.keySet())+ 1;
        } catch (NoSuchElementException e) {
            //If first ID manually we need to assign.
            nextId = 1L;
        }
        return nextId;
    }
}
