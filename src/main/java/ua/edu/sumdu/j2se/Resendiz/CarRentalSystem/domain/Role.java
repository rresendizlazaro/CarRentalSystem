package ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;


@Entity
@Data
@Table(name="role")
public class Role implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idRole;
    
    @NotEmpty
    private String name;
}
