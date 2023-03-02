package edu.uniandes.miso.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@ToString
public class FoodRoutine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFoodRoutine;
    @ManyToOne
    @JoinColumn(name = "idFoodPlan")
    private FoodPlan foodPlan;
    @ManyToOne
    @JoinColumn(name = "idFoodFrecuency")
    private FoodFrecuency foodFrecuency;
    @ManyToOne
    @JoinColumn(name = "idFood")
    private Food food;
    private String name;
    private String description;

}
