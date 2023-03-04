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
import javax.persistence.ManyToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class FoodRoutine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFoodRoutine;
	// @ManyToOne
	// @JoinColumn(name = "idFoodPlan")
	// private FoodPlan foodPlan;
	@ManyToOne
	@JoinColumn(name = "idFoodFrecuency")
	private FoodFrecuency foodFrecuency;

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
			name = "food_routine_food",
			joinColumns = @JoinColumn(name = "idFoodRoutine"),
            inverseJoinColumns = @JoinColumn(name = "idFood")
	)
	private List<Food> food = new ArrayList<>();
	private String name;
	private String description;

}
