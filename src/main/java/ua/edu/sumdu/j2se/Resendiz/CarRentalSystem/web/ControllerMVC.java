package ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.web;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.domain.CUser;
import ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.domain.Car;
import ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.service.CarService;
import ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.service.RoleService;
import ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.service.UserService;

@Controller
public class ControllerMVC {

    //Injection of the interface
    @Autowired
    private CarService carService;
    private UserService userService;
    
    @GetMapping("/home")
    public String home(Model model){
        var cars = carService.listCars();
        model.addAttribute("cars", cars);
        return "home";
    }
    
    @GetMapping("/logup")
    public String registerUser(Model model) {
        model.addAttribute("user", new CUser());
        return "register";
    }

    @PostMapping("/process_register")
    public String processRegister(@Valid CUser user, Errors errors) {
        if (errors.hasErrors()) {
            return "register";
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userService.save(user);

        return "register_success";
    }

    //Login
    @GetMapping("/login")
    public String log() {
        return "login";
    }

    //Index w auth
    @GetMapping("/")
    public String start(Model model, @AuthenticationPrincipal User user) {
        var cars = carService.listCars();
        model.addAttribute("cars", cars);
        return "index";
    }

    //path to update or add
    @GetMapping("/add")
    //Spring creates a new instance of car
    public String add(Car car) {
        return "modify";
    }

    //Path to save new car info
    @PostMapping("/save")
    public String save(@Valid Car car, Errors errors) {
        if (errors.hasErrors()) {
            return "modify";
        }
        carService.save(car);
        return "redirect:/";
    }

    @GetMapping("/edit/{idCar}")
    public String edit(Car car, Model model) {
        car = carService.findCar(car);
        model.addAttribute("car", car);
        return "modify";
    }

    @GetMapping("/delete")
    public String delete(Car car) {
        carService.delete(car);
        return "redirect:/";
    }
}
