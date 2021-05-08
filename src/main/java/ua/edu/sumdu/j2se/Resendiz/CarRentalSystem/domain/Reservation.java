package ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "reservation")
public class Reservation implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;
    
    @NotNull
    private int idUser;
    @NotNull
    private int idCar;
    @NotNull
    private int number;
    @NotNull
    private String start_time;
    @NotNull
    private String end_time;
    
    private int total;
}
