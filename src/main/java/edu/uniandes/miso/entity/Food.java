package edu.uniandes.miso.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="food")
@Getter
@Setter
@ToString
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFood;
    private String nameFood;
    private BigDecimal calories;
    private String description;
    private Long idUbication;
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    List<FoodType> listFoodType = new ArrayList<>();
}
