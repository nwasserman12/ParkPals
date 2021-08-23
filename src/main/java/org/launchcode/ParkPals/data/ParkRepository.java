package org.launchcode.ParkPals.data;

import org.launchcode.ParkPals.models.Dog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkRepository extends CrudRepository<Dog, Integer> {
}
