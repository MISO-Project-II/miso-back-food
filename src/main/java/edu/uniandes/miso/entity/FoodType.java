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
import javax.persistence.Table;

@Entity
@Table(name = "foodtype")
@Getter
@Setter
@ToString
public class FoodType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFoodType;
	private String name;
	private Integer isVegan;
	private Integer isVegetarian;

}
