package ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    //Role configuration
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                .withUser("admin")
                    .password("{noop}1234")
                    .roles("ADMIN","USER")
                .and()
                .withUser("user")
                    .password("{noop}1234")
                    .roles("USER")
                ;
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/edit/**", "/add/**", "/delete")
                    .hasRole("ADMIN")
                .antMatchers("/")
                    .hasAnyRole("USER","ADMIN")
                .and()
                    .formLogin()
                    .loginPage("/login")
                .and()
                .exceptionHandling().accessDeniedPage("/errors/403");
                ;
    }
}
