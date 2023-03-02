package edu.uniandes.miso.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class FoodPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFoodPlan;
    private String name;
    private String description;
    private String planType;
}
