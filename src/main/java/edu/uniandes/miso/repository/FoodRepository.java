package edu.uniandes.miso.repository;

import org.springframework.data.repository.CrudRepository;

import edu.uniandes.miso.entity.Food;


public interface FoodRepository extends CrudRepository<Food, Long> {
}
