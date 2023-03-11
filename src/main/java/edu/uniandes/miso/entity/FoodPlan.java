package edu.uniandes.miso.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
    private String contractType;
    private String eventType;
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = { CascadeType.PERSIST})
    @JoinTable(
            name = "foodplan_food_routine",
            joinColumns = @JoinColumn(name = "idFoodPlan"),
            inverseJoinColumns = @JoinColumn(name = "idFoodRoutine")
    )
    private List<FoodRoutine> foodRoutineList = new ArrayList<>();
}
