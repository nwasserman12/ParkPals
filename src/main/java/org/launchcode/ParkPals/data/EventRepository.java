package org.launchcode.ParkPals.data;

import org.launchcode.ParkPals.models.Dog;
import org.launchcode.ParkPals.models.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {
}
