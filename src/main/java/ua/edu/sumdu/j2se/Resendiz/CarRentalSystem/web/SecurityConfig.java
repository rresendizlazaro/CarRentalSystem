package ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.constants.Constants;
import ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.constants.Constants;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    //Role configuration
    @Autowired
    private UserDetailsService userDetailsService;
    
    //Using encryption
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception{
        //User service configuration
        build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers(Constants.EDIT.getValue(), Constants.ADD.getValue(), Constants.DELETE.getValue())
                    .hasRole(Constants.ADMIN.getValue())
                .antMatchers(Constants.PATH.getValue())
                    .hasAnyRole(Constants.USER.getValue(),Constants.ADMIN.getValue())
                .and()
                    .formLogin()
                    .loginPage(Constants.LOGIN.getValue())
                .and()
                .exceptionHandling().accessDeniedPage(Constants.ERRORS.getValue())
                ;
    }
}
