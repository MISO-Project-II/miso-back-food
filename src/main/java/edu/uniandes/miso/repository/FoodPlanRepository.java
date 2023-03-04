package edu.uniandes.miso.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import edu.uniandes.miso.entity.FoodPlan;


public interface FoodPlanRepository extends CrudRepository<FoodPlan, Long> {

    @RestResource(exported = true)
    Iterable<FoodPlan> findAll();
}
