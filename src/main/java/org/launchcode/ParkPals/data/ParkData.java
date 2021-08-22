package org.launchcode.ParkPals.data;

import org.launchcode.ParkPals.models.Park;

import javax.persistence.Entity;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ParkData {
    private static final Map<Integer, Park> parks = new HashMap<>();

    public static Collection<Park> getAll() {
        return parks.values();
    }

    public static Park getById(int id) {
        return parks.get(id);
    }

    public static void add(Park park) {
        parks.put(park.getId(), park);
    }

    public static void remove (int id) {
        parks.remove(id);
    }
}
