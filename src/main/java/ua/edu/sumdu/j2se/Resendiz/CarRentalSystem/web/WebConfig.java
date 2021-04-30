package ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
    @Override
    public void addViewControllers(ViewControllerRegistry register){
        register.addViewController("/").setViewName("index");
        register.addViewController("/login");
        register.addViewController("/errors/403").setViewName("/errors/403");
    }
}