package ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.service;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.dao.UserDao;
import ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.domain.Role;
import ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.domain.CUser;

@Service("userDetailsService")
public class UserServiceImpl implements UserDetailsService, UserService{

    @Autowired
    private UserDao userDao;
    
    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CUser user = userDao.findByUsername(username);
        
        if(user == null){
            throw new UsernameNotFoundException("Username: " + username + "does not exists");
        }
        
        var roles = new ArrayList<GrantedAuthority>();
        
        for (Role role : user.getRoles()) {
            roles.add(new SimpleGrantedAuthority(role.getName()));
        }
        
        return new User(user.getUsername(), user.getPassword(), roles);
    }

    @Override
    @Transactional
    public void save(CUser user) {
        userDao.save(user);
    }
}
