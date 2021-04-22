package ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.domain;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "car")
public class Car implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CarId;
    private String Carname;
    private String make;
    private int model;
    private boolean available;
}
