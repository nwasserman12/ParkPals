package org.launchcode.ParkPals.data;

import org.launchcode.ParkPals.models.Dog;
import org.launchcode.ParkPals.models.Park;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkRepository extends CrudRepository<Park, Integer> {

    Park findByPlaceId(String placeId);
}