package ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.web;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.domain.Car;
import ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.service.CarService;

@Controller
public class ControllerMVC {
    
    //Injection of the interface
    @Autowired
    private CarService carService;
    
    @GetMapping("/")
    public String start(Model model, @AuthenticationPrincipal User user){
        var cars = carService.listCars();
        model.addAttribute("cars", cars);
        return "index";
    }
    
    //path to update or add
    @GetMapping("/add")
    //Spring creates a new instance of car
    public String add(Car car){
        return "modify";
    }
    
    //Path to save new car info
    @PostMapping("/save")
    public String save(@Valid Car car, Errors errors){
        if(errors.hasErrors()){
            return "modify";
        }
        carService.save(car);
        return "redirect:/";
    }
    
    @GetMapping("/edit/{idCar}")
    public String edit(Car car, Model model){
        car = carService.findCar(car);
        model.addAttribute("car", car);
        return "modify";
    }
    
    @GetMapping("/delete")
    public String delete(Car car){
        carService.delete(car);
        return "redirect:/";
    }
}
