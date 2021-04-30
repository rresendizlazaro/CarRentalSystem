package ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.domain.CUser;

public interface UserDao extends JpaRepository<CUser, Long>{
    CUser findByUsername(String username);
}
