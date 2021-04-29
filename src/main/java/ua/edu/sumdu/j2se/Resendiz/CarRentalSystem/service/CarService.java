package ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.service;

import java.util.List;
import ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.domain.Car;

public interface CarService {
    public List<Car> listCars();
    
    public void save(Car car);
    
    public void delete(Car car);
    
    public Car findCar(Car car);
}
