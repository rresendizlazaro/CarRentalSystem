package ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.domain.Car;

public interface CarDao extends JpaRepository<Car, Long>{
}
