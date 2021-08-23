package org.launchcode.ParkPals.data;

import org.launchcode.ParkPals.models.Dog;
import org.springframework.data.repository.CrudRepository;

public interface DogRepository extends CrudRepository<Dog, Integer> {
}

