package ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.Data;

//Creates al the methods for the class
@Data
@Entity
@Table(name = "car")
public class Car implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    //Indicating the id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCar;
    
    @NotEmpty
    private String name;
    @NotEmpty
    private String brand;
    @NotEmpty
    private String year;
    @NotEmpty
    private String availability;
    @NotNull
    private int price;
}
