package edu.uniandes.miso.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FoodPlanDto {
    private Long idFoodPlan;
    private String name;
    private String description;
    private String contractType;
    private String eventType;
    private Long idUser;
}
