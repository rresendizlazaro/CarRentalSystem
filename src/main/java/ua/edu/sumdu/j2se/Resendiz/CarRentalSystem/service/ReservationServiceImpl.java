package ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.dao.ReservationDao;
import ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.domain.Reservation;

@Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    private ReservationDao reservationDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Reservation> listReservations(int number) {
        return (List<Reservation>) reservationDao.findByIdUser(number);
    }
    
    @Override
    @Transactional
    public void save(Reservation reservation) {
        reservationDao.save(reservation);
    }

    @Override
    @Transactional
    public void delete(Reservation reservation) {
        reservationDao.delete(reservation);
    }

    @Override
    @Transactional(readOnly = true)
    public Reservation findReservation(Reservation reservation) {
        return reservationDao.findByIdUser(reservation.getIdUser());
    }
    
}
