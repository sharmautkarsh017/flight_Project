package com.example.flight.controllers;

import com.example.flight.dto.ReservationUpdateRequest;
import com.example.flight.entities.Reservation;
import com.example.flight.repos.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ReservationRestController {
    @Autowired
    ReservationRepository reservationRepository;
    @RequestMapping("/reservations/{id}")
    public Reservation findReservation(@PathVariable("id") Long id)
    {
        return reservationRepository.re(id);

    }
    @RequestMapping("/reservations")
    public Reservation updateReservation(@RequestBody ReservationUpdateRequest request)
    {
        Reservation reservation=reservationRepository.re(request.getId());
        reservation.setNumberOfBags(request.getNumberOfBags());
        reservation.setCheckedIn(request.getCheckedIn());
       return reservationRepository.save(reservation);

    }
}
