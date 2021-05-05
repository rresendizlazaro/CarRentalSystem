package ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;


@Entity
@Data
@Table(name="user")
public class CUser implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    
    @NotEmpty
    private String username;
    
    @NotEmpty
    private String password;
    
    //Relationship between user and role
    @OneToMany
    @JoinColumn(name="id_user")
    private List<Role> roles;
}
