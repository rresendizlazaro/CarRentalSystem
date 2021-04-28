package ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.dao;

import org.springframework.data.repository.CrudRepository;
import ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.domain.Car;

public interface CarDao extends CrudRepository<Car, Long>{
    
}
