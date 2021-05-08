package ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.service;

import java.util.List;
import ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.domain.Reservation;

public interface ReservationService {
    public List<Reservation> listReservations();
    
    public void save(Reservation reservation);
    
    public void delete(Reservation reservation);
    
    public Reservation findReservation(Reservation reservation);
}
