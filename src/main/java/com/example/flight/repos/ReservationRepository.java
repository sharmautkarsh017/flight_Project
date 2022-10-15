package com.example.flight.repos;

import com.example.flight.entities.Flight;
import com.example.flight.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    @Query("from Reservation where id=:fli")
    Reservation re(Long fli);
}

