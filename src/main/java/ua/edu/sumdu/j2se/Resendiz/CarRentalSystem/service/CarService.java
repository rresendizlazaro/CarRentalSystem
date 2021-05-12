package ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.service;

import java.util.List;
import org.springframework.data.domain.Page;
import ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.domain.Car;

public interface CarService {
    List<Car> listCars();
    
    void save(Car car);
    
    void delete(Car car);
    
    Car findCar(Car car);
    
    Page<Car> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
