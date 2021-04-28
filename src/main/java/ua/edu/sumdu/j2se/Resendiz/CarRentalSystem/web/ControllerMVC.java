package ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.dao.CarDao;
import ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.domain.Car;

@Controller
public class ControllerMVC {
    
    //Injection of the interface
    @Autowired
    private CarDao carDao;
    
    @GetMapping("/car")
    public String start(Model model){
        var cars = carDao.findAll();
        model.addAttribute("cars", cars);
        return "index";
    }
}
