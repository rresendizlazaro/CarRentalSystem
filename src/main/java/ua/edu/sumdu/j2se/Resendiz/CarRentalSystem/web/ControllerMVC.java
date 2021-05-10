package ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.web;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import static java.util.concurrent.TimeUnit.DAYS;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.dao.CarDao;
import ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.dao.ReservationDao;
import ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.dao.UserDao;
import ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.domain.CUser;
import ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.domain.Car;
import ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.domain.Reservation;
import ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.service.CarService;
import ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.service.ReservationService;
import ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.service.UserService;

@Controller
public class ControllerMVC {

    //Injection of the interface
    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ReservationDao reservationDao;

    //Users
    @GetMapping("/logup")
    public String registerUser(Model model) {
        model.addAttribute("user", new CUser());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid CUser userForm, Errors errors) {
        if (errors.hasErrors()) {
            return "register";
        }
        try {
            //Roles
            userService.save(userForm);
        } catch (Exception ex) {
            System.out.print(ex + "User not registered");
        }

        return "register_success";
    }

    @GetMapping("/success")
    public String success() {
        return "register_success";
    }

    //Login
    @GetMapping("/login")
    public String log() {
        return "login";
    }

    //Cars
    @GetMapping("/home")
    public String home(Model model) {
        var cars = carService.listCars();
        model.addAttribute("cars", cars);
        return "home";
    }

    //Index with auth
    @GetMapping("/")
    public String start(Model model, @AuthenticationPrincipal User user) {
//        var cars = carService.listCars();
//        model.addAttribute("cars", cars);
//        return "index";
        return findPaginated(1, "name", "asc", model);
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

    //Reservations
    @GetMapping("/bookCar/{idCar}")
    public String rent(Car car, Model model) {
        car = carService.findCar(car);
        model.addAttribute("car", car);
        model.addAttribute("reservation", new Reservation());
        return "reservation";
    }

    @PostMapping("/saveReservation")
    public String saveReservation(Reservation reservation, @AuthenticationPrincipal User user, Car car, Model model) {
        //Getting id user
        CUser cUser = userDao.findByUsername(user.getUsername());
        reservation.setIdUser(Math.toIntExact(cUser.getIdUser()));
        //Setting reservation number
        reservation.setNumber((int) (Math.random() * 100000000));
        //Setting price by days
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate start = LocalDate.parse(reservation.getStart_time(), formatter);
        LocalDate end = LocalDate.parse(reservation.getEnd_time(), formatter);
        int days = (int) ChronoUnit.DAYS.between(start, end);
        //Searching car
        Car nCar = new Car();
        nCar.setIdCar(Long.valueOf(reservation.getIdCar()));
        car = carService.findCar(nCar);
        //Changing availability
        car.setAvailability("false");
        carService.save(car);
        //Saving reservation
        reservation.setTotal(days * car.getPrice());
        reservationService.save(reservation);
        model.addAttribute("reservation", reservation);
        model.addAttribute("car", car);
        return "reservation_info";
    }

    @GetMapping("/checkReservation")
    public String check(Reservation reservation) {
        return ("reservations");
    }

    @PostMapping("/searchReservation")
    public String search(Reservation reservation, Model model, Car car) {
        try {
            Reservation checkReservation;
            checkReservation = reservationDao.findByNumber(reservation.getNumber());

            Car other = new Car();
            other.setIdCar(Long.valueOf(checkReservation.getIdCar()));
            car = carService.findCar(other);
            model.addAttribute("reservation", checkReservation);
            model.addAttribute("car", car);
        } catch (Exception ex) {
            System.out.println("Error");
        }
        return ("reservation_info");
    }

    @GetMapping("/deleteReservation")
    public String delete(Reservation reservation) {
        reservationService.delete(reservation);
        return "redirect:/";
    }

    //Pagination
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
            @RequestParam("sortField") String sortField,
            @RequestParam("sortDir") String sortDir, Model model) {
        int pageSize = 6;

        Page<Car> page = carService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Car> listCars = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listCars", listCars);

        return "index";
    }
}
