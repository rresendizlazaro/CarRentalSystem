package ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.domain.Reservation;

public interface ReservationDao extends JpaRepository<Reservation, Long> {
    Reservation findByIdUser(int number);
    Reservation findByNumber(int number);
}
