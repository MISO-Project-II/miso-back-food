package edu.uniandes.miso.repository;

import edu.uniandes.miso.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
