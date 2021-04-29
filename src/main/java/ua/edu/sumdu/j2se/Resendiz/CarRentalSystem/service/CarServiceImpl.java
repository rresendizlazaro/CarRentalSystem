package ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.dao.CarDao;
import ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.domain.Car;

@Service
public class CarServiceImpl implements CarService{

    @Autowired
    private CarDao carDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Car> listCars() {
        return (List<Car>) carDao.findAll();
    }

    @Override
    @Transactional
    public void save(Car car) {
        carDao.save(car);
    }

    @Override
    @Transactional
    public void delete(Car car) {
        carDao.delete(car);
    }

    @Override
    @Transactional(readOnly = true)
    public Car findCar(Car car) {
        return carDao.findById(car.getIdCar()).orElse(null);
    }
}
