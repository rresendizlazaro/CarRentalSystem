package ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.service;

import java.util.List;
import ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.domain.Role;

public interface RoleService {
    public List<Role> listRoles();
    
    public void save(Role role);
}
