package ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.constants.Constants;

@Configuration
public class WebConfig implements WebMvcConfigurer{
    @Override
    public void addViewControllers(ViewControllerRegistry register){
        //View configuration
        register.addViewController(Constants.PATH.getValue()).setViewName(Constants.INDEX.getValue());
        register.addViewController(Constants.HOME.getValue());
        register.addViewController(Constants.PATH.getValue()).setViewName(Constants.REGISTER.getValue());
        register.addViewController(Constants.PATH.getValue()).setViewName(Constants.RESERVATION.getValue());
        register.addViewController(Constants.LOGUP.getValue());
        register.addViewController(Constants.LOGIN.getValue());
        register.addViewController(Constants.ERRORS.getValue()).setViewName(Constants.ERRORS.getValue());
    }
}
