package edu.uniandes.miso.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InputFood {
    private Long idFood;
    private Long idFoodType;
    private String nameFood;
    private BigDecimal calories;
    private String description;
    private Long idUbication;
}
