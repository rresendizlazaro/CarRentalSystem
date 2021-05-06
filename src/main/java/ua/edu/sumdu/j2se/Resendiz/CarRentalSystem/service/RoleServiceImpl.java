package ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.dao.RoleDao;
import ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.domain.Role;

@Service
public class RoleServiceImpl implements RoleService{
    
    @Autowired
    private RoleDao roleDao;

    @Override
    @Transactional(readOnly = true)
    public List<Role> listRoles() {
        return (List<Role>) roleDao.findByName("ROLE_USER");
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }
    
}
