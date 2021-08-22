package org.launchcode.ParkPals.data;
import org.launchcode.ParkPals.models.Dog;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DogData {
    private static final Map<Integer, Dog> dogs = new HashMap<>();

    public static Collection<Dog> getAll() {
        return dogs.values();
    }

    public static Dog getById(int id) {
        return dogs.get(id);
    }

    public static void add(Dog dog) {
        dogs.put(dog.getId(), dog);
    }

    public static void remove (int id) {
        dogs.remove(id);
    }
}
