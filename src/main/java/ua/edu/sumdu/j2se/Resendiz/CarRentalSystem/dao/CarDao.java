package ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.domain.Car;

public interface CarDao extends JpaRepository<Car, Long>{
    Car findByName(String string);
    
    Car findByBrand(String string);
    
    Car findByYear(String string);
    
    Car findByAvailability(String string);
}
