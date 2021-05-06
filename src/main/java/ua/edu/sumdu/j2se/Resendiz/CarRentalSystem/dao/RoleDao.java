package ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.domain.Role;

public interface RoleDao extends JpaRepository<Role, Long>{
    Role findByName(String name);
}
