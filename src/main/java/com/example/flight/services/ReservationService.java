package com.example.flight.services;

import com.example.flight.dto.ReservationRequest;
import com.example.flight.entities.Reservation;

public interface ReservationService {
    public Reservation bookFlight(ReservationRequest request);
}
