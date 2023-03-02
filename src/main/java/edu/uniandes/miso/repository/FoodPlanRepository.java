package edu.uniandes.miso.repository;

import org.springframework.data.repository.CrudRepository;

import edu.uniandes.miso.entity.FoodPlan;

public interface FoodPlanRepository extends CrudRepository<FoodPlan, Long> {
}
