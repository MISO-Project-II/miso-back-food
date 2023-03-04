package edu.uniandes.miso.repository;

import org.springframework.data.repository.CrudRepository;

import edu.uniandes.miso.entity.FoodType;

public interface FoodTypeRepository extends CrudRepository<FoodType, Long> {
}
