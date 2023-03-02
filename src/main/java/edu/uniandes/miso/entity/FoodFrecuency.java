package edu.uniandes.miso.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
public class FoodFrecuency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFoodFrecuency;
    private Long minute;
    private Long hour;
    private String dayLetter;
}
